### 👨‍💻 JSON vs TOON: LLM Dostu Veri Temsili Üzerine Kendi Deneyimim 💾🤖

>Yazılımcı Olarak Bana Ne Kattı?

Bu projede, klasik ve herkesin bildiği **JSON** formatını, LLM’ler için tasarlanmış daha kompakt ve token-dostu bir temsil olan **TOON** ile yan yana getirerek, aynı veriyi iki farklı dünyada nasıl taşıdığımı, nasıl işlediğimi ve özellikle de token maliyeti açısından aradaki farkı çok somut şekilde gördüğüm bir deneyim yaşadım; burada da bu deneyimi kendi ağzımdan, sahada kod yazarken hissettiklerim üzerinden anlatmak istiyorum.

---

#### JSON Nedir, Ne Değildir? 🧩

JSON, benim için yıllardır HTTP API’lerinden konfigürasyona, event payload’larından loglara kadar her yerde kullandığım, “okuması kolay, yazması rahat, neredeyse tüm dillerle entegre” bir veri formatı ve bu açıdan baktığımda, yazılım dünyasının ortak dili gibi çalışan, hem insanlar hem makineler tarafından anlaşılabilir olmak üzere tasarlanmış bir metin temsili olarak hayatımda ciddi bir yere sahip.

- JSON, insan gözüyle rahatlıkla okunabilen, süslü parantezler ve köşeli parantezlerle hiyerarşik yapıyı açıkça gösteren, string tabanlı bir format olduğu için, hem debug sürecinde hem de sistemler arası entegrasyonlarda bana çok yüksek düzeyde şeffaflık sağlayan, dolayısıyla karmaşık veri yapılarında bile “ben bu verinin içinde ne dönüyor?” sorusuna saniyeler içinde cevap bulmamı kolaylaştıran bir temsil sunar.
- JSON, neredeyse tüm modern backend ve frontend teknolojileri (Spring, .NET, Node.js, React, Angular, mobil platformlar vb.) tarafından doğal olarak desteklendiği için, standartlaşma anlamında projelerimi farklı ekiplerle, farklı teknolojilerle veya üçüncü parti servislerle entegre ederken, araya ekstra dönüştürme katmanları ekleme ihtiyacını minimuma indiren, bu sayede mimariyi sade ve anlaşılır tutmama yardımcı olan güçlü bir köprü rolü üstlenir.
- JSON’un temel amacı, veri yapısını hem makinelerin sorunsuz parse edebileceği hem de geliştiricinin sahada hızlıca göz atarak neyin, hangi alanın, hangi değeri tuttuğunu anlayabileceği şekilde sunmak olduğu için, özellikle log kayıtlarında, HTTP response/request gövdelerinde ve test senaryolarında beni asla yalnız bırakmayan, günlük pratiklerde vazgeçilmez hale gelen genel amaçlı bir araç gibi davranır.
- JSON kullanılmadığında, yerine çok spesifik, niş veya sadece belirli platformlara uygun formatlar tercih edildiğinde, uzun vadede hem ekibin öğrenme maliyeti artabilir hem de entegrasyon karmaşası büyüyebileceği için, JSON’un olmadığı bir dünyada her bir sistemin kendi formatını öğrenmek zorunda kaldığım, standardizasyonun zayıfladığı ve debug süreçlerinin ağırlaştığı daha problemli bir ekosisteme doğru gidebileceğimi çok net görüyorum.
- JSON’un avantajı; okunabilirlik, yaygın destek, tooling zenginliği (IDE, Postman, curl, browser devtools, log viewer’lar vb.) ve öğrenme eğrisinin düşük olmasıyken, dezavantajı ise özellikle LLM gibi token bazlı çalışan sistemlerde, aynı bilgiyi taşıyan daha kompakt formatlara göre daha fazla karakter ve dolayısıyla daha fazla token tüketmesi ve bazı durumlarda gereksiz kabarıklığa sebep olmasıdır.

---

#### TOON Nedir, Ne Değildir? 🎭

TOON ise bu projede özellikle LLM’lerle çalışırken tanıştığım, JSON’a göre daha kompakt ve token tasarrufu sağlayan bir veri temsili yaklaşımı ve ben burada TOON’u, insan odaklı okunabilirlikten biraz fedakârlık yapıp, model odaklı verimliliği öne çıkaran, daha çok “LLM’in sevdiği format” gibi konumlandırıyorum.

- TOON, `toon4j` kütüphanesi ile kullandığım, POJO’larımı encode ederek daha sıkıştırılmış, model için optimize edilmiş bir metin çıktısı üreten ve bu sayede aynı veri setini JSON’a göre daha az karakter ve dolayısıyla daha az token ile temsil edebilen, özellikle büyük language model’lerle etkileşimde maliyet ve performans boyutlarını iyileştirmek için tercih edilebilen bir formattır.
- TOON’un temel amacı, veriyi insan gözüne değil, LLM’in tokenizer’ına göre düzenlemek, gereksiz süslü parantez, key string tekrarları vb. meta bilgileri azaltmak ve sonuçta “aynı anlamı taşıyan ama token sayısı daha düşük” bir temsil ile modeli besleyerek, hem yanıt sürelerini hem de maliyetleri optimize etmektir.
- TOON kullanılmadığında, yani her şeyi klasik JSON ile gönderdiğim senaryolarda, özellikle büyük profil, context veya uzun prompt’lar içinde modelin işlediği token sayısı gereksiz şekilde artabileceği için, hem performans açısından daha yavaş yanıt alma riskiyle karşılaşabiliyor hem de ücretlendirme modeline göre maliyetimin arttığını görebiliyorum; dolayısıyla TOON’u kullanmamak bir şeyleri bozmaz ama tokene duyarlı ortamlarda ciddi bir optimizasyon fırsatını kaçırmama sebep olur.
- TOON’un avantajı; veri hacmini token boyutunda azaltabilmesi, LLM çağrılarında giriş yükünü hafifletmesi, aynı context penceresi içinde daha fazla bilgiyi sığdırma imkânı vermesi ve bu sayede uzun diyaloglarda veya zengin veri yapılarında daha verimli bir etkileşim sağlaması iken, dezavantajı ise JSON kadar okunaklı olmaması, ekipteki herkesin anında gözle inceleyememesi ve tooling tarafında JSON kadar yaygın destek bulmamasıdır.
- TOON, yazılımcıya “model gözüyle veri düşünme” bakış açısı kazandırdığı için, sadece klasik API taşımacılığının ötesinde, prompt mühendisliği, context yönetimi, token ekonomisi gibi alanlarda da bir farkındalık oluşturuyor ve böylece ben veriyi sadece “client–server” arasında dolaşan bir yük olarak değil, “modelin neresine ne kadar yer ayıracağım, bu alanı nasıl daha verimli dolduracağım” soruları üzerinden daha derinlemesine düşünmeye başlıyorum.

---

#### Hangi Durumda JSON, Hangi Durumda TOON? ⚖️

Gerçek hayatta projelerimde JSON ve TOON’u birbirlerinin rakibi gibi değil, farklı ihtiyaçlara cevap veren iki araç olarak görüyorum; JSON’u herkesin bildiği, kolay debug edilen, dış sistemlerle entegrasyonda güven veren bir format olarak kullanırken, TOON’u daha çok “LLM’e özel, içeride optimizasyon için kullandığım, arkadaki motoru daha verimli çalıştıran” bir katman gibi konumlandırıyorum.

- JSON’u; REST API’ler, microservice’ler arası iletişim, loglama, konfigürasyon dosyaları, gözle kontrol edilmesi gereken payload’lar ve üçüncü parti entegrasyonların hemen hepsinde kullanmayı sürdürüyorum, çünkü bu alanlarda okunabilirlik, standartlaşma ve geniş ekosistem desteği benim için token tasarrufundan daha kritik hale geliyor.
- TOON’u ise; LLM’lere büyük profiller, kapsamlı kullanıcı bilgileri, geçmiş mesajlar veya ağır context bilgileri gönderdiğim, her token’in maliyet ve performans açısından anlamlı olduğu, prompt sınırlarının kritik hale geldiği senaryolarda devreye alıyor, böylece model tarafına daha yoğun içeriği daha dar bir token penceresi içinde aktarabilmeyi hedefliyorum.

---

#### JSON ve TOON Birlikte Nasıl Çalışıyor? 🔗

Bu projede, JSON ve TOON’u yan yana koyup, aynı POJO’dan çıkan iki farklı temsilin token bazında ne kadar fark oluşturduğunu ölçen ve bunu Spring AI + Ollama ile konuşan bir deneme ortamı kurdum; böylece teorik farkları değil, birebir pratik çıktıyı gözümle görmüş oldum.

Aşağıda kullandığım `docker-compose`, `application.yml` ve `JsonToonDemoController` kodlarımın ilgili parçalarını ve bunların bana ne kattığını özetliyorum. 👇

---

#### Docker ile Ollama’yı Ayağa Kaldırma 🐳

```yaml
# Docker Compose dosyasının hangi sürüm söz dizimini kullandığını belirtir; burada 3.8 sürümünün özelliklerinden faydalanacaksın.
version: '3.8'

# Uygulamada çalışacak tüm container servislerinin tanımlarının yer aldığı ana bloktur.
services:
  # Ollama LLM servisinin tanımını başlatan servis adıdır; bu isimle diğer servisler bu konteynıra referans verebilir.
  ollama:
    # Container’ın Docker içinde görünecek mantıksal adını belirler; log ve komutlarda bu isimle kullanırsın.
    container_name: ollama
    # Bu servis için hangi imajın kullanılacağını belirtir; burada Docker Hub’daki resmi ollama/ollama:latest imajı çekilir.
    image: ollama/ollama:latest
    # Host makine ile container arasındaki port eşleştirmelerini tanımlar.
    ports:
      # Host üzerindeki 11434 portunu container içindeki 11434 portuna yönlendirir; Spring uygulaman bu adres üzerinden Ollama’ya ulaşır.
      - "11434:11434"
    # Container içinde kalıcı tutulacak dosya/dizinlerin host veya named volume ile ilişkilendirilmesini sağlar.
    volumes:
      # ollama_models isimli volume’ü container içindeki /root/.ollama dizinine mount eder; modeller burada saklanarak container silinse bile kaybolmaz.
      - ollama_models:/root/.ollama  # isteğe bağlı ancak önerilir
    # Container’ı, Docker daemon veya makine yeniden başladığında otomatik ayağa kaldırır; sadece sen manuel olarak durdurursan tekrar kendiliğinden başlamaz.
    restart: unless-stopped

# Uygulamanın kullanacağı named volume tanımlarını içeren bloktur.
volumes:
  # ollama_models adında kalıcı bir volume oluşturur; Ollama model dosyaları burada tutulur ve container ömründen bağımsız olarak korunur.
  ollama_models:
```

- Bu `docker-compose` dosyasıyla, Ollama’yı izole bir container içinde ayağa kaldırıyor, port yönlendirmesi sayesinde Spring Boot uygulamamın `http://localhost:11434` adresi üzerinden modele ulaşmasını sağlıyor ve `ollama_models` isimli volume ile model dosyalarını kalıcı hale getirerek, container yeniden oluşturulduğunda bile indirilmiş modellerin korunmasını sağlayan, hem geliştirme ortamında hem de uzun soluklu denemelerde bana büyük rahatlık sunan bir altyapı kurguluyorum.

---

#### Spring Boot Yapılandırmam: Spring AI + Ollama + Llama2 ⚙️

```yaml
# Spring yapılandırma kök anahtarıdır; uygulamanın adı ve AI entegrasyonu gibi Spring’e özgü ayarlar burada toplanır.
spring:
  # Uygulamanın mantıksal adını tanımlayan bölüm; loglarda ve bazı Spring bileşenlerinde bu isimle görünecek.
  application:
    # Spring Boot uygulamasının adını 'spring-ai-toon' olarak belirler; projeyi diğer servislerden ayırt etmeyi kolaylaştırır.
    name: spring-ai-toon
  # Spring AI ile ilgili tüm yapılandırmaların toplandığı bölümdür; buradan Ollama gibi model sağlayıcılarını ayarlarsın.
  ai:
    # Spring AI’in Ollama entegrasyonuna özel ayarlarını barındırır; hangi URL’ye ve hangi modele gideceğini buradan kontrol edersin.
    ollama:
      # Ollama sunucusunun temel adresini tanımlar; Docker’da 11434 portunu açtığın için uygulama bu URL üzerinden modele istek yollar.
      base-url: http://localhost:11434
      # Sohbet (chat) tabanlı LLM çağrıları için kullanılan yapılandırma bloğudur.
      chat:
        # Chat isteklerinde kullanılacak varsayılan modeli 'llama2' olarak seçer; böylece her çağrıda modeli tek tek belirtmek zorunda kalmazsın.
        model: llama2

# Spring Boot gömülü sunucusunun (Tomcat vs.) hangi port üzerinden dinleyeceğini belirleyen kök ayardır.
server:
  # Uygulamanın HTTP isteklerini 9191 portu üzerinden almasını sağlar; böylece aynı makinede çalışan diğer servislerle port çakışmasını önleyebilirsin.
  port: 9191
```

- Bu yapılandırma ile, Spring Boot uygulamamı **Spring AI** üzerinden **Ollama** ile konuşturuyor, `base-url` olarak Docker’da ayağa kalkmış Ollama servisini işaret ediyor ve `llama2` modelini chat modeli olarak seçerek, uygulamam içinden tek bir bean üzerinden LLM çağrıları yapabilen, konfigürasyonu temiz, yönetilebilir ve gerektiğinde farklı modellere çok kolay switch edebileceğim bir AI katmanı inşa ediyorum.

---

#### JSON vs TOON Karşılaştırma Endpoint’i 🧪

```java
// Uygulamanın bu sınıfının ait olduğu paketi belirtir; namespace düzenini ve proje yapısını organize eder.
package tr.com.huseyinaydin.controller;

// JSON verisini Java objelerine ve tam tersi yöne dönüştürmek için Jackson'ın ObjectMapper sınıfını projeye dahil eder.
import com.fasterxml.jackson.databind.ObjectMapper;
// Kullanıcı profilini temsil edecek olan kendi DTO sınıfımızı içeri alır; hem JSON hem TOON için temel veri modelini sağlar.
import tr.com.huseyinaydin.dto.UserProfile;
// jtokkit kütüphanesinin encoding kayıtlarını yönetmek ve uygun tokenizer'ı almak için yardımcı sınıfını import eder.
import com.knuddels.jtokkit.Encodings;
// Tokenizer’ın encode işlemini gerçekleştiren Encoding arayüzünü kullanmak için gerekli olan import ifadesidir.
import com.knuddels.jtokkit.api.Encoding;
// Hangi tokenizer tipinin kullanılacağını (örneğin CL100K_BASE) belirlemek için EncodingType enum’unu projeye dahil eder.
import com.knuddels.jtokkit.api.EncodingType;
// Java objelerini TOON formatına encode etmek için kullanılan toon4j kütüphanesini projeye bağlar.
import im.arun.toon4j.Toon;
// Lombok’un sağladığı @Slf4j anotasyonunu kullanarak sınıfa otomatik log nesnesi eklememizi sağlar.
import lombok.extern.slf4j.Slf4j;
// Spring AI’in Ollama ile konuşmak için sağladığı chat model bean’ini kullanmak üzere import eder.
import org.springframework.ai.ollama.OllamaChatModel;
// Bu sınıfın bir REST controller olduğunu ve HTTP endpoint’leri tanımlayacağını göstermek için gerekli web anotasyonlarını içeri alır.
import org.springframework.web.bind.annotation.*;
// Dış HTTP isteklerini yapmak için Spring’in RestClient yardımcı sınıfını projeye dahil eder.
import org.springframework.web.client.RestClient;

// Koleksiyon yapıları (List, Map, LinkedHashMap vb.) için Java’nın util paketini topluca import eder.
import java.util.*;

// Bu sınıfın bir REST controller olduğunu, yani HTTP isteklerini karşılayacak endpoint’ler içerdiğini Spring’e bildirir.
@RestController
// Sınıf seviyesinde /api/demo path’i tanımlayarak, içerideki tüm endpoint’lerin bu yol altında gruplanmasını sağlar.
@RequestMapping("/api/demo")
// Lombok ile bu sınıfa log değişkeni (log.info, log.error vb.) otomatik enjekte edilmesini sağlar; manuel logger tanımı ihtiyacını ortadan kaldırır.
@Slf4j
public class JsonToonDemoController {

    // Ollama ile sohbet temelli LLM çağrıları yapmak için kullanılacak Spring AI model bean’ini final bir alan olarak tanımlar.
    private final OllamaChatModel ollama;
    // Ollama sunucusuna doğrudan HTTP çağrıları yapmak için temel URL’si önceden tanımlanmış bir RestClient oluşturur.
    private final RestClient rest = RestClient.create("http://localhost:11434");
    // JSON serialize/deserialize işlemleri için kullanılacak ObjectMapper örneğini oluşturur.
    private final ObjectMapper mapper = new ObjectMapper();

    // Metinleri tokenize etmek için kullanılacak encoding stratejisini jtokkit üzerinden CL100K_BASE tokenizer’ı ile yapılandırır.
    private final Encoding encoder =
            Encodings.newDefaultEncodingRegistry().getEncoding(EncodingType.CL100K_BASE);

    // Controller sınıfının ihtiyacı olan OllamaChatModel bağımlılığını constructor üzerinden alarak dependency injection uygular.
    public JsonToonDemoController(OllamaChatModel ollama) {
        // DI ile gelen OllamaChatModel örneğini sınıfın final alanına atar.
        this.ollama = ollama;
    }

    // /api/demo/json-vs-toon endpoint’ine HTTP GET isteği geldiğinde bu metodu çalıştıracak şekilde eşleştirme yapar.
    @GetMapping("/json-vs-toon")
    // Endpoint’in döndüreceği cevabın, anahtar-değer çiftlerinden oluşan generic bir Map yapısı olacağını belirtir.
    public Map<String, Object> compareJsonVsToon() {
        try {
            // ---------- 1. Basit POJO ----------

            // Kullanıcıya ait isim, yaş, ülke, teknoloji stack’i ve adres bilgilerini tutan UserProfile nesnesini oluşturur.
            UserProfile profile = new UserProfile(
                    // Kullanıcı adı ve soyadını profile içine yerleştirir.
                    "Hüseyin AYDIN",
                    // Kullanıcının yaş bilgisini profile ekler.
                    31,
                    // Kullanıcının ülke bilgisini 'Türkiye' olarak ayarlar.
                    "Türkiye",
                    // Kullanıcının uzmanlık ve ilgi duyduğu teknolojileri liste halinde profile’a ekler.
                    List.of("Spring Boot", "Spring Framework", "Kafka", "Flyway Migration", "Liquibase Migration", "Microservices", "gRPC", "Kubernetes", "Docker"),
                    // İç içe adres objesi oluşturarak mahalle, il ve posta kodu bilgilerini Nested Address sınıfına doldurur.
                    new UserProfile.Address("Kurdunus", "Niğde", "51200")
            );

            // ---------- 2. JSON ----------

            // `profile` nesnesini güzel formatlanmış (pretty-printed) bir JSON string’e dönüştürür.
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(profile);

            // Üretilen JSON string’ini loglara yazarak hem debug hem de görsel kontrol imkânı sağlar.
            log.info("JSON Değeri: {}", json);

            // ---------- 3. TOON ----------

            // Aynı `profile` nesnesini TOON formatına encode eder; böylece LLM için daha kompakt bir temsil elde edilir.
            String toon = Toon.encode(profile);
            // Elde edilen TOON çıktısını loglara yazarak JSON ile arasındaki farkı inceleme imkânı verir.
            log.info("TOON Değeri: {}", toon);

            // ---------- 4. LM Studio'nun tokenizer'ını kullanarak token sayımı ----------

            /*
            Token dediğimiz şey, metnin yapay zekânın anlayıp işleyebileceği formata dönüştürülmüş en küçük parçalarından her biridir.
            Tokenize edilmesi, toon içindeki metnin modelin anlayacağı en küçük parçalara (token’lara) bölünmesi demektir.
            Tokenize edilmesi sonucu oluşan token sayısı, metnin encoder tarafından bölündüğü en küçük birimler olan token’ların toplam adedini ifade eder.
             */

            // JSON string’ini seçilen tokenizer ile encode ederek, kaç token’dan oluştuğunu hesaplar.
            int jsonTokens = encoder.encode(json).size();
            // JSON için hesaplanan token sayısını loglara yazar; karşılaştırma için referans bir değer oluşturur.
            log.info("JSON Giriş Belirteçleri: {}", jsonTokens);
            // TOON string’ini de aynı tokenizer ile işleyerek, onun kaç token ürettiğini hesaplar.
            int toonTokens = encoder.encode(toon).size();
            // TOON için elde edilen token sayısını log’layarak JSON ile arasındaki farkı görünür kılar.
            log.info("TOON : {}", toonTokens);

            // ---------- 5. OllamaChatModel kullanılarak sohbet işleme/processing ----------

            // JSON formatındaki profili, LLM’e “bu profili özetle” tarzında bir komutla gönderir ve modelden gelen özeti yakalar.
            String jsonSummary = ollama.call("Summarize this profile:" + json);
            // Aynı profili TOON formatında göndererek, LLM’in TOON temsili üzerinden ürettiği özeti elde eder.
            String toonSummary = ollama.call("Summarize this profile:" + toon);

            // ---------- 6. Yanıt oluştur ----------

            // Döndürülecek cevabı, sıralı anahtar-değer yapısını korumak için LinkedHashMap kullanarak oluşturur.
            Map<String, Object> result = new LinkedHashMap<>();
            // Cevap gövdesine, referans amaçlı ham JSON payload’ını ekler.
            result.put("json_payload", json);
            // Cevap gövdesine, referans amaçlı TOON payload’ını ekler.
            result.put("toon_payload", toon);
            // JSON için hesaplanan token sayısını yanıt içine dahil ederek, dışarıya ölçülebilir veri sunar.
            result.put("input_json_tokens", jsonTokens);
            // TOON için hesaplanan token sayısını yanıt içine ekler; böylece token bazlı kıyaslama yapılmasını sağlar.
            result.put("input_toon_tokens", toonTokens);
            // JSON ve TOON token sayıları arasındaki farkı hesaplayarak, potansiyel tasarrufu gösterir.
            result.put("token_savings", jsonTokens - toonTokens);
            // Modele JSON ile gönderilen profilin özetini, yanıt altında bilgi amaçlı yayınlar.
            result.put("json_summary", jsonSummary);
            // Modele TOON ile gönderilen profilin özetini, yanıt altında ikinci bir karşılaştırma metriği olarak sunar.
            result.put("toon_summary", toonSummary);
            // Hazırlanan tüm verileri içeren Map yapısını HTTP cevabı olarak döner.
            return result;
        } catch (Exception e) {
            // Herhangi bir hata meydana geldiğinde, hata mesajını tek alanlı bir Map içinde dönerek basit bir error response üretir.
            return Map.of("error", e.getMessage());
        }
    }
}
```

- Bu controller’da, önce basit ama gerçekçi bir `UserProfile` POJO’su oluşturarak, ad, yaş, ülke, teknoloji stack’i ve adres bilgisi gibi alanlarla dolu, LLM için anlamlı bir profil obje seti yaratıyor ve böylece hem JSON hem TOON için aynı başlangıç kaynağını kullanarak adil bir kıyaslama yapıyorum.
- Ardından Jackson `ObjectMapper` ile bu profili JSON’a çeviriyor, pretty-print edilmiş haliyle log’lara basarak, insan gözüyle kontrol edilebilir ve gerekirse Postman veya başka araçlar üzerinden rahatça test edilebilir bir string çıktısı elde ediyor ve bu sayede JSON’un klasik okunabilirliğini koruyorum.
- Sonrasında `Toon.encode(profile)` çağrısıyla aynı `UserProfile` nesnesini TOON formatına encode ediyor, TOON string’ini log’a yazıp, JSON’a göre nasıl daha kompakt veya farklı bir yapıda göründüğünü gözlemleme şansı buluyor ve böylece TOON’un LLM odaklı doğasını çıplak gözle hissedebiliyorum.
- `jtokkit` kütüphanesini kullanarak, `EncodingType.CL100K_BASE` tokenizer’ı ile hem JSON hem TOON string’lerini encode ediyor, `encoder.encode(...).size()` çağrılarıyla iki formatın token sayısını ölçüyor ve log’lara yazarak, teorik tartışmadan çıkıp “bu veri için JSON şu kadar, TOON bu kadar token tutuyor” diyebildiğim somut bir metrik üretiyorum.
- `OllamaChatModel` üzerinden, `Summarize this profile:` komutuyla hem JSON hem TOON payload’larını LLM’e gönderiyor, her iki formatla da modelden özet alarak, sadece token sayısını değil, modelin algıladığı içeriğin işlenip işlenmediğini de pratikte test ediyor ve bu sayede TOON’un sadece kompakt değil aynı zamanda işlevsel bir alternatif olup olmadığını kendi gözümle doğruluyorum.
- Son aşamada, `Map<String, Object>` içine JSON ve TOON payload’larını, her birinin token sayısını, aradaki token farkını ve modelden gelen özetleri ekleyerek, `/api/demo/json-vs-toon` endpoint’i üzerinden hem insan gözüyle inceleyebileceğim hem de başka test araçlarıyla otomatik analiz edebileceğim, zengin ve karşılaştırmaya elverişli bir cevap yapısı döndürüyorum.

---

#### JSON vs TOON Karşılaştırma Tablosu 📊

Aşağıdaki tabloda, JSON ile TOON’u kendi gözlemlerim ve bu proje üzerinden yaptığım denemeler ışığında yan yana koyarak özetliyorum:

| Başlık                     | JSON                                                                                                                                                                                                 | TOON                                                                                                                                                                                                                           |
|----------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Okunabilirlik 👀          | JSON, anahtar–değer çiftlerinin açıkça görülebildiği, süslü parantezlerle hiyerarşinin kolay takip edildiği, hem junior hem senior seviyedeki geliştiricilerin birkaç saniyede neyin nerede olduğunu anlayabildiği, insan odaklı bir formattır. | TOON, insan tarafından doğrudan okunmaya JSON kadar uygun olmayan, daha çok LLM ve tokenizer optimizasyonu için tasarlanmış, dolayısıyla günlük debug işlerinde değil, model odaklı veri taşıma katmanında öne çıkan bir formattır. |
| Token Kullanımı 🧮        | JSON, alan isimleri ve yapısal karakterler sebebiyle, aynı mantıksal veriyi taşırken daha fazla karakter ve dolayısıyla daha fazla token tüketebilir, bu da LLM çağrılarında context penceresini zorlayıp maliyeti artırabilir.                 | TOON, daha kompakt bir temsil sunduğu için, aynı veri için token sayısını azaltmayı hedefler; bu da özellikle büyük profiller veya uzun prompt’lar gönderirken daha fazla bilgiyi aynı pencereye sığdırmamı ve maliyetten tasarruf etmemi sağlar. |
| Standartlaşma 🌐          | JSON, web dünyasında fiili standart haline gelmiş, neredeyse her dil ve framework tarafından out-of-the-box desteklenen, üçüncü parti servislerle entegrasyonda çok az sürpriz çıkaran, geniş ekosistemli bir formattır.                      | TOON, daha özel ve belirli kütüphaneler üzerinden kullanılan bir format olduğu için, JSON kadar yaygın değildir; bu da onu özellikle LLM odaklı iç kullanım senaryolarında daha mantıklı, dış entegrasyonlarda ise daha sınırlı hale getirir. |
| LLM Entegrasyonu 🤖       | JSON, LLM’lere gönderildiğinde de gayet iyi çalışır, ancak model açısından gereksiz yapısal yük ve tekrarlı anahtar stringleri token penceresini doldurabilir, bu da özellikle geniş context’li senaryolarda verimliliği azaltabilir.         | TOON, LLM’e giden payload’ın token boyutunu optimize etmeyi amaçladığı için, özellikle sık çağrılı, yüksek hacimli veya token başına ücretlendirilen ortamlarda ciddi bir performans ve maliyet avantajı sağlayabilen daha hedefli bir çözümdür. |
| Debug ve Geliştirici Deneyimi 🛠️ | JSON, loglarda, Postman isteğinde, browser devtools’ta veya herhangi bir HTTP client’ta incelenmesi son derece kolay olduğu için, ekip içi iletişimde ve hata ayıklamada neredeyse bir “ortak dil” görevi görür ve günlük geliştirme temposuna çok iyi oturur. | TOON, doğrudan okunabilirlikten ziyade verimlilik odaklı olduğu için, hata ayıklama aşamasında genellikle JSON kadar konfor sağlamaz; bu nedenle genellikle uygulama içinde otomatik işlenen bir katmanda, geliştiricinin sadece sonuç metriklerine (token sayısı vb.) baktığı bir araç olarak kullanılması daha uygundur. |
| Kullanılmadığında Ne Kaçırılır? ⚠️ | JSON kullanılmadığında, yerine çok egzotik veya sadece belirli platformlara özgü formatlar getirildiğinde, ekiplerin öğrenme maliyeti artar, entegrasyon zorluğu yükselir ve debug süreçleri uzar; dolayısıyla JSON’u tamamen bırakmak çoğu zaman mantıklı değildir. | TOON kullanılmadığında, sistem çalışmaya devam eder ancak LLM çağrılarında daha fazla token tüketilir, context penceresi daha çabuk dolar ve tokene duyarlı ortamlarda potansiyel performans ve maliyet optimizasyonu fırsatları kaçırılır; yani TOON bir “olmazsa olmaz” değil ama “varsa ciddi artı” sağlar. |

---

#### Yazılımcı Olarak Bana Ne Kattı? 👨‍💻

- JSON ve TOON’u yan yana kullanmak, veriye sadece “API payload’ı” olarak değil, aynı zamanda “modelin beynine giden yakıt” olarak bakmamı sağladı; böylece context yönetimi, token ekonomisi ve LLM odaklı veri tasarımı gibi konularda, klasik backend bakış açısının ötesine geçen, daha bütüncül bir düşünme disiplini kazandım.
- `JsonToonDemoController` içinde token sayımı yaparak, JSON ve TOON arasındaki farkı somut sayılarla gördüğüm için, gelecekte LLM entegrasyonlu projelerde hangi senaryoda hangi formatı tercih edeceğime dair çok daha veriye dayalı, rasyonel ve ölçülebilir kararlar verebilecek bir referans noktası oluşturmuş oldum.
- Spring AI, Ollama, Llama2, JSON, TOON ve `jtokkit` gibi parçaları aynı projede bir araya getirmek, modern Java ekosisteminde hem klasik web geliştirme hem de yapay zeka entegrasyonu tarafında elimde ne kadar güçlü kombinasyonlar olduğunu görmemi sağladı ve bu da beni, ileride daha karmaşık AI destekli mikroservisler tasarlama konusunda motive etti.

---

#### Sonuç 🧾

Özetle; **JSON**, genel amaçlı, herkesin bildiği, okunabilirlik ve entegrasyon kolaylığı sağlayan bir format olarak projelerimde yerini korumaya devam ederken, **TOON** ise LLM merkezli senaryolarda, token tasarrufu ve performans avantajı sunan, daha niş ama bir o kadar da stratejik bir araç olarak yanına eklenmiş durumda; bu proje ile ikisini aynı anda kullanarak, hem klasik backend reflekslerimi hem de modern AI odaklı veri tasarımı bakış açımı aynı potada eriten, oldukça öğretici bir deneyim yaşamış oldum.