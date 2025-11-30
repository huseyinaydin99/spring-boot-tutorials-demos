### ⚡ Hazelcast Cache ile Spring Boot Uygulamamı Uçururken Not Aldıklarım

Bu dokümanda, kendi projemde kullandığım **Hazelcast tabanlı cache yapısını** hem mimari açıdan hem de kod seviyesinde nasıl kurguladığımı, neleri neden tercih ettiğimi ve Spring Boot ile nasıl bir araya getirdiğimi adım adım anlatıyorum. Cache tarafında özellikle **Hazelcast + Spring Cache abstraction** kombinasyonunu kullanarak, veritabanı yükünü azaltan, response sürelerini düşüren ve geliştirici olarak bana ciddi konfor sağlayan bir yapı kurdum. 🚀

---

#### 🧠 Hazelcast Cache Nedir, Ne Değildir?

Hazelcast’i bu projede, **veritabanından sık okunan ama her seferinde tekrar hesaplanmasına gerek olmayan verileri, bellek üzerinde tutan dağıtık bir cache katmanı** olarak konumlandırıyorum; yani uygulamam için “hızlı erişim sağlayan, ölçeklenebilir ve akıllı bir önbellek altyapısı” gibi düşünebilirim.

- 🔹 **Hazelcast cache, veriyi doğrudan RAM’de tutan, key–value mantığıyla çalışan ve birden fazla node arasında paylaştırılabilen bir bellek tabanlı veri deposudur;** dolayısıyla, her okuma isteğinde veritabanına gitmek yerine, çoğu zaman veriyi milisaniyeler içinde bellekten çekerek uygulamanın genel performansını ciddi şekilde iyileştirmeyi hedeflerim.
- 🔹 **Hazelcast, tek başına bir veritabanı değildir ve verinin kalıcılığı (durability) konusunda bir RDBMS’in yerini almaz;** ben bu yapıyı, kalıcı veritabanımın (örneğin MySQL) önünde duran ve özellikle “okuma yoğunluklu” senaryolarda veriyi hızlandıran bir katman olarak kullanıyorum.
- 🔹 **Hazelcast’in sunduğu eviction, TTL, backup, dağıtık cluster gibi özellikler sayesinde, cache sadece basit bir HashMap olmaktan çıkar,** bunun yerine sistem yüküne göre kendini yöneten, gereksiz veriyi atan ve node’lar arasında veri kopyalayabilen bilinçli bir altyapıya dönüşür.

---

#### 🎯 Hazelcast Cache Ne Amaçla Vardır, Neden Kullanıyorum?

Bu projede Hazelcast kullanmaktaki temel amacım, **veritabanına binen gereksiz yükü azaltarak hem uygulamanın tepki süresini düşürmek hem de altyapının daha az kaynakla daha fazla isteği taşıyabilmesini sağlamak** oldu.

- 🎯 **Sık erişilen ama çok sık değişmeyen verileri (örneğin kullanıcı listeleri, belirli id ile çağrılan kullanıcı nesneleri) her seferinde veritabanından çekmek yerine, Hazelcast üzerinde saklayarak, read operasyonlarını RAM hızına taşıyorum;** bu sayede veritabanı CPU ve I/O yükü düşerken, kullanıcıya verdiğim cevap süresi de gözle görülür biçimde kısalıyor.
- 🎯 **Dağıtık uygulama senaryolarında, birden fazla instance çalıştırdığımda bile cache’i node bazlı değil, cluster bazlı yönetmek istiyorum;** Hazelcast burada, farklı instance’ların aynı cache’i paylaşmasını sağlayarak hem tutarlılığı artırıyor hem de her node’un kendi kafasına göre ayrı ayrı cache üflemesini engelliyor.
- 🎯 **Spring Cache abstraction ile Hazelcast’i birleştirerek, iş mantığımı bozmadan, sadece anotasyonlar ile metot bazlı cache kontrolü yapabiliyorum;** böylece kod tarafında minimal değişiklikle maksimum performans kazancı elde etmeyi hedefliyorum.

---

#### 🚫 Hazelcast Cache Kullanılmazsa Ne Olur?

Eğer bu projede Hazelcast cache kullanmasaydım, uygulamanın davranışı hâlâ doğru olurdu ama **özellikle okuma yoğunluklu senaryolarda gereksiz yere veritabanına yük bindirmiş ve tepki süresini ihtiyaçtan fazla uzatmış olurdum.**

- 🚫 **Her `getUsers()` çağrısında `repository.findAll()` ile veritabanına gitmek, özellikle kullanıcı sayısı arttıkça ve istek hacmi yükseldikçe hem veritabanının CPU/IO kaynaklarını tüketir hem de uygulamanın cevap verme süresini gereksiz yere uzatır;** dolayısıyla sadece “doğru çalışıyor” demek bana yetmez, “doğru ve hızlı çalışıyor mu?” sorusunun cevabını da önemserim.
- 🚫 **Kullandığım altyapı büyüyüp, mikroservislerin sayısı arttığında, cache olmadan tüm trafiği veritabanına yıkmak, ölçeklenebilirlik problemlerini daha erken ve daha sert yaşamama sebep olurdu;** bu da horizontal scale yerine veritabanını büyütmeye, replika kurmaya ve daha ağır altyapı yatırımlarına zorlayabilirdi.
- 🚫 **Kullanıcı tarafında, özellikle listeleme ve sık çağrılan endpoint’lerde latency artar, zaman zaman spike’lar oluşur ve UI tarafında “ara ara takılan” bir deneyim ortaya çıkardı;** bu da aslında tamamen cache ile çözülebilecek bir problem iken gereksiz yere son kullanıcının konforunu bozan bir duruma dönüşebilirdi.

---

#### 🎯 Hazelcast Cache’in Ana Amacı Nedir?

Hazelcast cache’in ana amacı, **okuma ağırlıklı iş yüklerini veritabanından RAM katmanına kaydırarak, aynı veriyi tekrar tekrar hesaplamadan veya sorgulamadan, mümkün olan en yüksek hızla servis etmektir.**

- 🧩 **Benim bakış açımla Hazelcast, veritabanı ile kullanıcı arayüzü arasında duran, verinin “sıcak kopyalarını” üzerinde taşıyan ve metot çağrılarımın sonuçlarını akıllıca saklayıp gerektiğinde bana yeniden sunan akıllı bir tampon katmandır;** yani hem veri tabanımı korur hem de servislerimin hızını kullanıcıya hissettiren görünmez bir hızlandırıcı gibi davranır.
- 🧩 **Spring Cache ile birlikte Hazelcast kullanarak, “hangi metotlar cache’lensin, hangi anahtarlarla saklansın, ne zaman temizlensin” gibi mantıksal kararları anotasyon seviyesinde verebiliyor olmam,** ana amacın sadece performans değil, aynı zamanda yönetilebilirlik ve okunabilirlik olduğunu da gösteriyor.

---

#### 📌 Hangi Durumlarda Hazelcast Kullanmayı Tercih Ediyorum?

Hazelcast’i özellikle, **sık çağrılan, verisi çok hızlı değişmeyen ve her istekte aynı işi tekrar tekrar yapmanın anlamsız olduğu** senaryolarda devreye alıyorum.

- ✅ **Kullanıcı listesi, ürün listesi, sabit ayarlar, sık erişilen detay kayıtlar gibi, verinin görece stabil olduğu ve “her seferinde DB’ye gitmek yerine cache’ten okumak çok daha mantıklı” dediğim noktalar, Hazelcast için biçilmiş kaftandır;** bu sayede hem backend tarafında gereksiz tekrarları keserim hem de UI'ya daha hızlı cevap veririm.
- ✅ **Aynı parametrelerle tekrar tekrar çağrılan, computation maliyeti yüksek veya DB sorgusu ağır olan metotları, `@Cacheable` ile işaretleyip Hazelcast’e emanet ettiğimde,** CPU ve IO tüketimim anlamlı şekilde azalır ve daha iyi ölçeklenebilir bir mimari elde ederim.
- ✅ **Okuma–yazma oranının ciddi şekilde okuma lehine kaydığı, yani “çok okuyorum, az yazıyorum” dediğim domain’lerde,** Hazelcast devreye girdiğinde, nadiren yapılan güncelleme ve silme işlemlerine karşılık çok sık yapılan okuma operasyonlarını cache üzerinden çözüp büyük kazanç elde ederim.

---

#### 👨‍💻 Yazılıma ve Programcıya Ne Katar?

Hazelcast kullanmak sadece uygulamayı hızlandırmakla kalmıyor, **geliştirici olarak bana da önemli miktarda mimari esneklik ve temizlik kazandırıyor.**

- 💡 **Spring Cache abstraction sayesinde, iş mantığımı bozmadan, sadece anotasyonlarla cache davranışını tanımlayabiliyor olmam, kodun okunabilirliğini ve bakımı kolaylığını ciddi şekilde artırıyor;** metot gövdeleri veriye odaklanırken, performans optimizasyonu anotasyon seviyesinde ifade edilmiş oluyor.
- 💡 **Hazelcast ile birlikte eviction, TTL, max size policy gibi kavramlarla çalışmak, beni “veriyi sadece saklayan” değil, aynı zamanda “veriyi yaşam döngüsüyle birlikte yöneten” bir geliştirici moduna geçiriyor;** bu da ileride daha büyük sistemler kurgularken işime yarayacak bir düşünme biçimi kazandırıyor.
- 💡 **Dağıtık cache kavramına erken aşamada alışmak, mikroservis mimarilerine geçtiğimde, instance sayısı arttığında ve çoklu node senaryolarında neyle karşılaşacağımı önceden görmemi sağlıyor;** böylece sadece tek makinede çalışan küçük bir uygulamaya değil, ileriye dönük ölçeklenebilir bir mimariye zihnen hazırlanmış oluyorum.

---

#### ⚖️ Hazelcast Cache Özellikleri, Avantajları ve Dezavantajları

Aşağıda, bu projede hissettiğim temel özellik, avantaj ve dezavantajları birkaç başlık altında topluyorum.

#### Özellikler 🧩

- 🔸 **Dağıtık, bellek tabanlı ve key–value odaklı bir veri yapısı sunar;** bu sayede veriyi RAM üzerinde hızlıca tutarken, birden fazla node’un aynı veriyi paylaşabilmesine imkân tanır.
- 🔸 **Eviction policy (LRU vb.), TTL, max size policy gibi ayarlarla verinin ne kadar süre ve hangi koşullarda tutulacağını ince ayar yapmama izin verir;** böylece hem RAM kullanımını kontrol eder hem de gereksiz veri birikimini engellerim.
- 🔸 **Spring Boot ile entegre çalışabildiği için konfigürasyon tarafında çok uğraştırmaz;** `Config`, `MapConfig`, `EvictionConfig` gibi sınıflarla koddan konfigurasyon yapabildiğim gibi, YAML/JSON tabanlı config dosyalarıyla da aynı davranışı sağlayabilirim.

#### Avantajlar ✅

- ✅ **Performans tarafında, özellikle okuma yoğunluklu endpoint’lerde hissedilir düzeyde iyileşme sağlar;** veritabanına gitmeden RAM üzerinden cevap vermek, kullanıcı deneyiminde doğrudan fark edilen bir hız kazancı getirir.
- ✅ **Veritabanının yükünü azalttığı için, aynı altyapı üzerinde daha fazla kullanıcı trafiğini kaldırabilir,** ölçeklenebilirlik konusunda daha rahat hareket edebilirim ve bazen sadece cache ekleyerek bile ek donanım ihtiyacını erteleyebilirim.
- ✅ **Spring Cache anotasyonlarıyla birlikte kullanıldığında, kodun içine dağılmış karmaşık cache yönetimi yerine, belirli noktalarda toplanmış, okunabilir ve bakımı kolay bir cache stratejisi ortaya çıkar;** böylece hem yeni gelen geliştiriciler sistemi daha rahat anlar hem de ben geçmişte ne düşündüğümü anotasyonlar üzerinden okuyabilirim.

#### Dezavantajlar / Dikkat Edilmesi Gerekenler ⚠️

- ⚠️ **Cache yanlış kullanıldığında tutarsız veri (stale data) problemi ortaya çıkabilir;** bu yüzden özellikle `@CacheEvict` noktalarının ve TTL sürelerinin dikkatle belirlenmesi gerekir, aksi hâlde kullanıcıya eski veri gösterme riski doğar.
- ⚠️ **Her veri tipi için cache uygun değildir;** çok hızlı değişen, kritik tutarlılık gerektiren veya her istekte güncel olması zorunlu olan veriler için cache kullanmak yerine doğrudan veritabanına gitmek daha doğru olabilir, dolayısıyla “her şeyi cache’leyeyim” yaklaşımı zarar da verebilir.
- ⚠️ **Hazelcast gibi dağıtık bir yapıyı devreye almak, belli bir öğrenme eğrisi gerektirir;** eviction politikaları, cluster topolojisi, backup sayısı gibi konulara hakim olmadan prod ortamında agresif ayarlar yapmak, istenmeyen yan etkiler doğurabilir.

---

#### 🤝 Hazelcast + Spring Cache + Veritabanı: Üçü Bir Arada Nasıl Çalışıyor?

Bu projede asıl hoşuma giden kısım, **Spring Cache anotasyonları, Hazelcast konfigürasyonu ve veritabanının bir orkestrasyon gibi birlikte çalışması.**

- 🔗 **Veritabanım (MySQL) verinin kalıcı kaynağı (source of truth) iken, Hazelcast bu verinin sık kullanılan kopyalarını bellek üzerinden servis ediyor;** Spring Cache ise bu iki katmanı birbirine bağlayan “karar mekanizması” olarak, hangi metotların ne zaman cache’e yazılıp ne zaman cache’ten okunacağını belirliyor.
- 🔗 **`@Cacheable` anotasyonu ile, “bu metot sonucunu cache’e koy ve aynı parametrelerle çağrılırsa cache’ten getir” diyorum;** Hazelcast burada sadece altyapıyı sağlıyor, işin ne zaman cache’e yazılacağı veya ne zaman silineceği Spring Cache tarafından yönetiliyor.
- 🔗 **`@CacheEvict` anotasyonu ile de, “bu metot çalıştığında şu anahtara ait cache kaydını sil” diyerek, veri değiştiği zaman cache’in de güncellenmesini sağlıyorum;** böylece veritabanı update/delete operasyonlarıyla cache arasındaki tutarlılığı yönetebiliyorum.

---

#### 🧾 Projemde Hazelcast Yapılandırması (CacheConfig.java)

Bu projede Hazelcast’i Java config ile şöyle tanımladım:

```java
package tr.com.huseyinaydin.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizePolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean
    public Config hazelcastConfig() {
        return new Config()
                .setInstanceName("hazelcast-instance")
                .addMapConfig(
                        new MapConfig()
                                .setName("userCache")
                                .setTimeToLiveSeconds(2000)
                                .setEvictionConfig(
                                        new EvictionConfig()
                                                .setSize(200) // limit
                                                .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
                                                .setEvictionPolicy(EvictionPolicy.LRU)
                                )
                );
    }

    /*
     Genel anlamı: “Tahliye”, “çıkarma”, “kovma”.
     Cache / Hazelcast / bellek yönetimi bağlamında:
     “Eviction” = “(ön)bellekten atma” / “(ön)bellekten çıkarma” politikası.
     Yani belirli kurallara göre, artık tutulmayacak verilerin önbellekten silinmesi / atılması işlemi.
    */

    // LRU (Least Recently Used) politikası, bellek ya da önbellek dolduğunda
    // en uzun süredir kullanılmayan veriyi ilk olarak sistemden atan stratejidir.
}
```

#### Bu konfigürasyonda:

- **`setInstanceName("hazelcast-instance")` ile, uygulama içerisinde kullanacağım Hazelcast instance’ına anlamlı bir isim veriyorum;** bu isim hem loglarda hem de yönetim tarafında işime yarıyor.
- **`setName("userCache")` ile, kullanıcı verisini tutacağım map’in (cache’in) adını belirliyorum;** Spring tarafında `@Cacheable(cacheNames = { "userCache" })` dediğimde de bu map devreye giriyor.
- **`setTimeToLiveSeconds(2000)` ayarıyla, cache’e giren her kaydın maksimum 2000 saniye yaşayacağını, bu süre dolduğunda otomatik olarak düşeceğini belirtiyorum;** böylece sonsuza kadar birikmeyen, belirli aralıklarla kendini yenileyen bir cache elde ediyorum.
- **`EvictionConfig` içinde `setSize(200)` ve `setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)` ile, belleğin ücretsiz kısmına göre 200 birimlik bir sınır tanımlıyorum ve `EvictionPolicy.LRU` ile de “en uzun süredir kullanılmayan kaydı önce at” diyorum;** yani hem TTL ile zamana göre hem de LRU ile kullanım sıklığına göre bir temizlik stratejisi oluşturmuş oluyorum.

---

#### 🧩 Servis Katmanında Cache Stratejim (UserService)

Servis katmanında, cache’i Spring Cache anotasyonları ile şu şekilde kullanıyorum:

```java
package tr.com.huseyinaydin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import tr.com.huseyinaydin.dao.UserRepository;
import tr.com.huseyinaydin.model.User;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Cacheable(cacheNames = { "userCache" })
    public List<User> getUsers() {
        return repository.findAll();
    }

    @Cacheable(value = "userCache", key = "#id", unless = "#result==null")
    public User getUser(int id) {
        return repository.findById(id).get();
    }

    @CacheEvict(value = "userCache")
    public String delete(int id) {
        repository.deleteById(id);
        return "Kullanıcı silindi ID: " + id;
    }
}
```

#### Burada akış şu şekilde çalışıyor:

- **`getUsers()` metodu üzerinde `@Cacheable(cacheNames = { "userCache" })` kullandığım için, ilk çağrıda `repository.findAll()` ile veritabanına gidiliyor ve dönen `List<User>` sonucu `userCache` içerisine tek bir anahtarla kaydediliyor;** sonrasında aynı metot tekrar çağrıldığında, cache kaydı silinmediği sürece veritabanına gitmeden doğrudan cache’ten dönüyor ve sistem ciddi şekilde hızlanıyor.
- **`getUser(int id)` metodunda `@Cacheable(value = "userCache", key = "#id", unless = "#result==null")` kullanarak, her kullanıcıyı kendi `id` değeriyle ayrı bir cache kaydı hâline getiriyorum ve eğer sonuç `null` ise cache’e hiç yazmayarak gereksiz (boş) kayıt tutmamayı tercih ediyorum;** böylece hem lookup işlemlerini hızlandırıyor hem de hatalı/verisiz durumları cache’e taşımamış oluyorum.
- **`delete(int id)` metoduna `@CacheEvict(value = "userCache")` ekleyerek, silinen kullanıcıya ait cache kaydını da otomatik temizliyorum;** burada Spring, metot parametrelerinden aynı key’i (id) üretip ilgili entry’yi silerken, ben `allEntries = true` demediğim için diğer kullanıcıların cache kayıtları olduğu gibi kalıyor ve sadece nokta atışı bir temizlik yapılmış oluyor.

---

#### 🌐 Controller Üzerinden Akış (UserController)

En üst katmanda, REST endpoint’ler ise şu şekilde:

```java
package tr.com.huseyinaydin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.com.huseyinaydin.model.User;
import tr.com.huseyinaydin.service.UserService;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot
 *
 */

@RestController
@RequestMapping("/cache-api")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return service.getUsers();
    }

    @GetMapping("/getUserById/{id}")
    public User getUser(@PathVariable int id) {
        return service.getUser(id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        return service.delete(id);
    }
}
```

Burada:

- **`/cache-api/getAllUsers` endpoint’i, arka planda `getUsers()` metodunu çağırıyor ve bu metot Hazelcast cache sayesinde çoğu zaman veritabanına gitmeden cevap veriyor;** bu da özellikle listelerin sık çekildiği panellerde ve admin ekranlarında önemli bir hız artışı sağlıyor.
- **`/cache-api/getUserById/{id}` endpoint’i, her kullanıcıyı kendi `id` değeriyle cache’lediğim `getUser(int id)` metodunu çalıştırıyor;** aynı kullanıcı tekrar istendiğinde, veritabanı yerine Hazelcast üzerinden okuma yapılıyor ve response süresi gözle görünür biçimde kısalıyor.
- **`/cache-api/deleteUser/{id}` endpoint’i, ilgili kullanıcıyı hem veritabanından siliyor hem de `@CacheEvict` sayesinde ilgili cache kaydını temizliyor;** böylece kullanıcı silindikten sonra tekrar `getUsers()` veya `getUser(id)` çağrılarında eskimiş veri dönme riski en aza indiriliyor.

---

#### 🧾 Küçük Bir Özet Tablosu

Aşağıdaki tablo, bu projede kurguladığım yaklaşımı tek bakışta özetliyor:

| Katman / Bileşen        | Rolü ve Katkısı                                                                                                                                                                                                                                                                 |
|-------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Veritabanı (MySQL)**  | Uygulamadaki verinin kalıcı olarak saklandığı, transaction’ların işlendiği ve nihai tutarlılığın korunduğu katmandır; cache yalnızca hız için vardır, asıl doğruluk kaynağı veritabanıdır ve veri kaybı veya bozulma durumunda sistemin toparlanacağı referans nokta burasıdır. |
| **Hazelcast Cache**     | Sık erişilen ve çok sık değişmeyen verilerin RAM üzerinde tutulduğu, TTL ve eviction politikaları ile yönetilen bir hızlandırma katmanıdır; okuma isteklerinin önemli bir kısmını veritabanından çekmek yerine bellekten karşılayarak hem performansı hem de ölçeklenebilirliği artırır. |
| **Spring Cache Anotasyonları** | `@Cacheable` ve `@CacheEvict` gibi anotasyonlar sayesinde, hangi metotların cache’leneceğini, hangi anahtarla saklanacağını ve ne zaman temizleneceğini iş mantığını bozmadan, deklaratif ve okunabilir bir şekilde tanımlamamı sağlar; böylece kod sade kalırken davranış esnekliği artar. |

---

> Bu yapı ile, **Hazelcast cache’i sadece teorik bir kavram olarak değil, Spring Boot uygulamam içinde somut bir performans aracı olarak kullanmış oluyorum;** hem kod tarafında sade ve anlaşılır bir yapı elde ediyorum hem de veritabanıma gereksiz yük bindirmeden kullanıcıya hızlı cevap veren bir API ortaya çıkarıyorum. 🚀🔥  
