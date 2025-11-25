#### 🧩 Saga Design Pattern, Orchestration ve Choreography
_Mikroservis dünyasında tutarlılık, iş akışı ve veri bütünlüğünü kendi bakış açımdan anlatıyorum._

Bu dokümanda, **monolith’te transaction mantığı**, **Saga design pattern’in dağıtık dünyada neyi çözdüğü**, **database transaction rollback ile telafi (compensation) işlemleri arasındaki fark**, **eventual consistency (nihai tutarlılık)** kavramı ve **orchestration / choreography** yaklaşımlarını, **kendi ağzımdan**, yazılım geliştirici gözüyle detaylı bir şekilde anlatıyorum. 💻⚙️


---

#### 🏛 Monolith’te Transaction Mantığı Nasıl Çalışır?

Monolith mimaride, genel olarak **tek bir uygulama** ve çoğunlukla **tek bir veritabanı** vardır; bu dünyada transaction kavramı, benim gözümde, tüm işi saran ve hata olduğunda her şeyi tek hamlede geri alabilen güçlü bir koruma kalkanı gibidir. 🛡️

- Monolith yapılarda, **iş akışı boyunca yapılan tüm veritabanı işlemleri tek bir transaction bloğu içinde çalıştırılır**, yani birkaç farklı tabloya insert, update veya delete yapıyor olsam bile hepsi tek bir “mantıksal işlem” olarak görülür ve bu transaksiyon commit edilene kadar dış dünya bu değişiklikleri kesin olarak görmez.

- Transaction bitmeden önce en sonda bir hata aldığımda, **veritabanı altyapısının sunduğu rollback mekanizmasıyla tüm adımlar geriye sarılır**, böylece sanki o transaction hiç çalışmamış gibi olur ve veritabanı transaction başlamadan önceki tutarlı haline geri döner.

- Bu yapı sayesinde monolith dünyasında **ACID garantilerini** (Atomicity, Consistency, Isolation, Durability) oldukça güçlü şekilde hissederim; özellikle “Atomicity” benim için “ya hep ya hiç” kuralını temsil eder ve bir işin bütün basamakları ya birlikte başarılı olur ya da birlikte iptal edilir.

- Monolith mimaride transaction yönetimi genellikle **tek bir veritabanı motoru ve tek bir connection context** üzerinden yürüdüğü için, performans ve doğruluk anlamında son derece deterministik ve öngörülebilir bir davranış üretir; bu da bana debug ve hata analizinde büyük konfor sağlar.

- Ancak sistem büyüyüp, farklı domain’ler, farklı ölçekleme ihtiyaçları, farklı takımlar ve bağımsız deploy edilmesi gereken parçalar ortaya çıktığında, **monolith’in tek transaction’lı, sıkı bağlı dünyası** esnekliğini kaybetmeye ve beni geliştirme-deploy tarafında kısıtlayan bir faktör haline gelmeye başlar.


---

#### 🌐 Saga’da (Dağıtık Dünyada) Transaction Mantığı Nasıl Evrilir?

Mikroservis mimarisine geçtiğimde, artık **tek veritabanı ve tek transaction** lüksüm ortadan kalkar; her servis kendi veritabanına sahip olmak ister ve bu noktada **Saga design pattern** devreye girer. 🧩

- Saga, benim için **monolith dünyasındaki tek ve büyük transaction mantığının, dağıtık ve mikroservis tabanlı mimariye uyarlanmış halidir**, yani uzun soluklu bir iş akışını, birbirine bağlı ama bağımsız çalışan küçük adımlara (local transaction’lara) böler.

- Saga’da, **her mikroservis kendi veritabanında kendi local transaction’ını yönetir**, bu transaction adımı başarılı olursa bir sonraki mikroservise geçilir; dolayısıyla artık tek bir global, atomik transaction yoktur, yerine birbirine zincirlenmiş, adım adım ilerleyen bir iş süreci vardır.

- İş akışı, örneğin **Sipariş → Ödeme → Stok → Kargo** gibi sıralı bir senaryoya bölünür ve her adım, ilgili mikroservisin sorumluluğundadır; ben bu yapıyı tasarlarken, **single responsibility (tek sorumluluk) prensibini** mümkün olduğunca korumaya çalışırım.

- Saga pattern’i kullanırken, **bir adımda hata olduğunda tüm sistemi tek seferde rollback etmek yerine**, daha önce başarılı olmuş adımlar için **telafi (compensation) işlemleri** çalıştırılır; bu da transaksiyon kavramının teknik olarak veritabanından alınıp, iş mantığı seviyesine (business level) taşınması anlamına gelir.

- Saga, monolith’te alıştığım “tek transaction, hızlı rollback” konforunu bire bir vermez, ancak **dağıtık, ölçeklenebilir, bağımsız deploy edilebilen mikroservisler dünyasında, tutarlılıktan tamamen vazgeçmeden yaşayabilmem için tasarlanmış bir çözüm** olarak bana çok güçlü bir mimari yaklaşım sunar. 🚀


---

#### 🔁 Database Transaction Rollback vs Telafi (Compensation) İşlemleri

Saga’yı anlamak için, **klasik veritabanı transaction rollback** ile **Saga’daki compensation işlemlerini** kafamda net ayırmam gerektiğini görüyorum. ⚖️

- Database transaction rollback, **tamamen veritabanı motorunun sorumluluğunda olan, fiziksel değişiklikleri geri saran ve transaction log’ları üzerinden çalışan düşük seviye (low-level) bir mekanizmadır**; bu mekanizmada, aynı transaction içinde yapılan tüm değişiklikler tek bir komutla geri alınır ve dış dünyaya hiçbir yan etki bırakılmamış gibi olur.

- Telafi (compensation) işlemleri ise, **iş mantığı seviyesinde tanımladığım, önceki adımın etkisini mantıksal olarak tersine çevirmeye çalışan ayrı operasyonlardır**; örneğin ödeme alındıysa “refundPayment”, stok düşüldüyse “releaseStock”, sipariş açıldıysa “cancelOrder” gibi, tamamen yeni transaction’lar olarak çalışan, ek adımlar üretmem gerekir.

- Rollback kavramı, veritabanı içinde, **aynı transaction context** içerisinde çalıştığı için, sonucu çoğu zaman deterministiktir ve “daha önce yapılan hiçbir şey gerçekleşmemiş gibi” hissi verir; buna karşılık compensation işlemleri, **yeni transaction’lar** olduğu için, ağ hataları, üçüncü parti servis sorunları veya domain kısıtları nedeniyle bazen kısmen başarısız olabilir ve bu nedenle işin doğasında “en iyiye yakın, kabul edilebilir düzeyde tutarlılık” fikri vardır.

- Veritabanı transaction rollback’i genellikle **mikrosaniye ile milisaniye düzeyinde** ve oldukça hızlı çalışırken, compensation işlemlerinde **mesaj kuyrukları, event’ler, yeniden denemeler ve diğer servislerle iletişim** olduğu için, bu süreç saniyeler, hatta bazı karmaşık senaryolarda daha uzun zaman dilimleri alabilir.

- Bu nedenle, ben artık Saga dünyasında, **veri tutarlılığını “tek hamlede, anında sağlanan bir garanti” olarak değil, iyi tasarlanmış telafi adımları ve süreçler sayesinde “nihai olarak ulaşılacak bir hedef” olarak düşünmeye başlıyorum**; bu da beni doğal olarak **eventual consistency** kavramına götürüyor.


---

#### ⏳ Eventual Consistency (Nihai Tutarlılık) Nedir?

Saga ile birlikte, **anlık (strong) tutarlılık** garantisinden, zaman içinde ulaşılacak **nihai tutarlılık (eventual consistency)** modeline geçtiğimi kabul etmem gerekir. ⌛

- Eventual consistency, benim gözümde, **sistemdeki tüm mikroservislerin ve veritabanlarının her zaman aynı anda aynı veriyi göstermesini beklememek**, bunun yerine zaman içinde mesajlar, event’ler ve telafi adımları işlendikçe sistemin tutarlı bir duruma yakınsayacağını kabul etmek demektir.

- Bu modelde, bir kullanıcı bir işlemi gerçekleştirdiğinde, **bazı alt sistemler bu değişikliği hemen görürken, diğer alt sistemlerin bu değişiklikten haberdar olması için belli bir süre geçmesi** gerekebilir; bu geçiş süresinde, sistem kısa süreli “görünürde tutarsız” durumlar yaşayabilir.

- Ben, eventual consistency kullanan bir mimaride, **okumalar (read) ve yazmalar (write) için kullanıcı deneyimini ve beklentiyi** doğru yönetmek zorunda olduğumu biliyorum; örneğin, “siparişiniz işleniyor” gibi ara durumlar göstermek, kullanıcının zihninde sistemin durumu hakkında gerçekçi bir çerçeve oluşturur.

- Eventual consistency, bana **yüksek erişilebilirlik, esneklik ve ölçeklenebilirlik** kazandırırken, aynı zamanda **geliştirici olarak zihinsel modelimi değiştirmemi, “her an her yerde aynı veri var” takıntısından çıkmamı** zorunlu kılar; bu da özellikle monolith’ten mikroservise geçen biri için önemli bir bakış açısı dönüşümüdür.

- Nihai olarak, ben bu modelde şunu kabulleniyorum: **“Veri kısa süreliğine tutarsız görünebilir, ama doğru tasarlanmış Saga akışları, telafi adımları ve event işleme mantıkları sayesinde, sistem zaman içinde tutarlı ve anlamlı bir duruma mutlaka yakınsar.”** ✅


---

#### 🧠 Saga Design Pattern: Nedir, Ne Değildir, Ne Amaçla Vardır?

Saga’yı, mikroservis dünyasında **dağıtık iş akışlarını güvenle yönetmemi sağlayan, iş odaklı bir transaction mimarisi** olarak görüyorum. 🎯

- Saga, benim için **monolith’teki tek transaction kavramının, mikroservisler arası iş süreçlerine uyarlanmış, adım adım yürüyen ve telafi adımlarıyla desteklenen bir tasarım şablonudur**; yani transaction’ı “veritabanı sınırından çıkarıp, iş akışı üzerine yerleştiren” bir yaklaşım sunar.

- Saga pattern’i, **farklı mikroservislerin bağımsız veritabanlarına rağmen, tek bir iş sürecini uçtan uca tutarlı bir şekilde çalıştırabilmek**, hata olduğunda ise “hiç yapılmamış” seviyesine mümkün olduğunca yakın bir iş mantığı durumu elde etmek amacıyla kullanılır.

- Saga, **tek bir global ACID transaction’ı taklit etmeye çalışmaz**, bunun yerine, “**her adım kendi local transaction’ına sahiptir, hata olursa daha önceki adımlar için telafi (compensation) operasyonları çalışır**” prensibiyle, dağıtık dünyada gerçekçi ve uygulanabilir bir çözüm sunar.

- Bu pattern’i kullanmazsam, mikroservisler arası zincirleme iş akışlarında **yarım kalmış siparişler, alınmış ama iade edilmemiş ödemeler, düşülmüş ama iade edilmemiş stoklar, başlatılamamış kargolar** gibi, hem kullanıcıyı hem de beni zor durumda bırakacak, tutarsız ve karmaşık veri durumlarıyla uğraşmak zorunda kalabilirim.

- Saga’nın ana amacı, **dağıtık bir sistemde iş süreci tutarlılığını, veri bütünlüğünü ve geri alınabilirliği (compensability) iş mantığı seviyesinde modelleyip, mikroservislerin bir bütün olarak uyum içinde davranmasını sağlamak**, bunu yaparken de bağımsız deploy, gevşek bağlılık ve esneklik gibi mikroservis avantajlarından vazgeçmememi sağlamaktır.


---

#### 🔍 Saga Hangi Durumlarda Kullanılmalı, Yazılımcıya Ne Katar?

Saga, her iş için değil, özellikle **uzun, çok adımlı ve birden fazla mikroservisin katıldığı kritik iş süreçleri** için anlamlı hale gelir. 🧭

- Ben Saga’yı genellikle, **sipariş oluşturma, ödeme alma, stok ayırma, kargo başlatma, rezervasyon yapma, bilet kesme, kullanıcı kayıt akışı gibi, birden fazla servisin ve veritabanının koordinasyonuyla yürüyen, iş açısından kritik süreçlerde** kullanırım; çünkü bu tür akışlarda hata durumunda yarım iş bırakmak, hem kullanıcı memnuniyeti hem de finansal riskler açısından kabul edilebilir değildir.

- Basit, tek adımlı ya da sadece tek mikroservis ve tek veritabanı üzerinde dönen işlemler için Saga kullanmak, benim gözümde gereksiz karmaşıklıktır; böyle durumlarda klasik transaction’lar, hatta bazen sadece basit “retry” mekanizmaları yeterli olabilir.

- Saga, geliştirici olarak bana;
    - **İş süreçlerini açık, görülebilir ve test edilebilir bir şekilde tanımlama**,
    - **Hata senaryolarını en baştan düşünmeye zorlama**,
    - **Her adım için telafi (compensation) mantığını net yazma** gibi disiplinler kazandırır ve bu da uzun vadede kod kalitemi ve sistem tasarım becerimi ciddi şekilde güçlendirir.

- Bu pattern’i benimsediğimde, **loglama, izlenebilirlik (observability), distributed tracing, correlation id gibi kavramların ne kadar hayati olduğunu** daha iyi anlarım; çünkü dağıtık bir Saga’nın içinde, hangi siparişin hangi aşamada takıldığını, hangi servisin telafi adımında patladığını görebilmek, üretim ortamında sakin kalmam için hayati öneme sahiptir.

- Sonuç olarak, Saga bence sadece bir “pattern” değil, aynı zamanda **bana olgun bir mikroservis mimarisi düşünme biçimi kazandıran bir zihniyet dönüşümüdür**; böylece sistemi sadece “metotlar ve sınıflar” üzerinden değil, “iş süreçleri ve akışlar” üzerinden de modellemeye başlarım.


---

#### ⚙️ Orchestration: Merkezî Beyinli Saga Yaklaşımı

**Orchestration**, Saga’yı hayata geçirirken, ortada **merkezî bir orkestratör (orchestrator)** bulunan yaklaşımı temsil eder. 🧠🎼

- Orchestration yaklaşımında, **Saga akışının tamamını yöneten özel bir bileşen** (Saga Orchestrator) tasarlarım; bu bileşen hangi adımın ne zaman çalışacağını, hangi mikroservisin hangi sırayla çağrılacağını ve hata durumunda hangi telafi adımlarının devreye gireceğini bilir.

- Örneğin, bir e-ticaret akışında, orchestrator sırasıyla:  
  `createOrder` → `payOrder` → `reserveStock` → `startShipping` çağrılarını yapar;  
  herhangi bir adımda hata aldığında ise, önceden tanımlı telafi zincirine göre,  
  `cancelShipping` → `releaseStock` → `refundPayment` → `cancelOrder` gibi geri dönüş adımlarını sırayla çağırır.

- Orchestration’ın bana sağladığı en büyük avantaj, **iş akışının tek bir merkezde, tek bir kod bloğunda toplanmış olmasıdır**; bu sayede akışı okumak, anlamak, debug etmek ve test etmek görece daha kolay hale gelir, çünkü “hikâyenin tamamı” orchestrator içinde yazılıdır.

- Ancak bu yaklaşımın dezavantajı, **tüm akışın merkezî bir bileşene bağımlı hale gelmesi**dir; orchestrator çok büyürse, zamanla “dağıtık monolith’in beyni” gibi davranmaya başlayabilir ve bu da bakım, genişletme ve performans anlamında yeni riskler doğurabilir.

- Ben orchestration yaklaşımını, özellikle **kritik, net tanımlı ve sıkı kontrol gerektiren Saga akışlarında** tercih ederim; çünkü böyle durumlarda, tek bir yerde toplanmış, okunabilir bir akış mantığı, hem ekip içi iletişimi hem de operasyonel müdahaleyi ciddi anlamda kolaylaştırır.


---

#### 💃 Choreography: Event Tabanlı, Dağıtık Saga Yaklaşımı

**Choreography** yaklaşımı, orkestratöre sahip olmak yerine, **servislerin event’ler üzerinden birbirleriyle haberleştiği**, daha dağıtık ve esnek bir Saga modelidir. 🎭📯

- Choreography’de, ortada akışı yöneten tek bir beyin yoktur; bunun yerine, **her mikroservis belirli event’leri dinler ve bu event’ler gerçekleştiğinde kendi sorumluluğuna giren adımı icra eder**, ardından gerekirse kendi event’ini üretir; böylece tüm sistem bir nevi dans koreografisi yapar gibi uyum içinde akışını sürdürür.

- Örnek bir akışta, sipariş servisi `OrderCreated` event’ini yayınlar, ödeme servisi bu event’i dinleyip ödemeyi alır ve `PaymentCompleted` event’ini üretir; stok servisi `PaymentCompleted` event’ini dinleyip stok ayırır ve `StockReserved` event’ini üretir, kargo servisi ise `StockReserved` event’ini dinleyip kargo sürecini başlatır ve `ShipmentStarted` event’ini üretir.

- Hata durumunda da yine event tabanlı telafi akışları çalışır; örneğin stok ayırma başarısız olursa `StockReservationFailed` event’i yayınlanır, bu event’i dinleyen ödeme servisi **refund** işlemini yapar, sipariş servisi ise siparişi iptal eder; böylece, telafi akışı bile dağıtık bir şekilde dans etmeye devam eder.

- Choreography’nin bana en büyük katkısı, **gevşek bağlılık (loose coupling)** sağlamasıdır; yeni bir servis eklemek istediğimde, sadece ilgili event’e abone olur ve kendi işini yapar, bu sayede merkezi orchestrator’ı değiştirmeden sistemi genişletebilirim.

- Ancak bu modelin dezavantajı, **akışın mantığını anlamanın zorlaşmasıdır**; hangi event’ten sonra hangi servislerin devreye girdiğini, hangi event zincirinin hangi sonucu doğurduğunu takip etmek için iyi bir dokümantasyon, sağlam bir event şeması ve güçlü bir observability altyapısına ihtiyaç duyduğumu bilirim; aksi halde sistem “event spagetti” diye tabir edilen karmaşık ve yönetilmesi zor bir hale gelebilir.


---

#### 📊 Orchestration vs Choreography Karşılaştırma Tablosu

Aşağıda, kendi bakış açımdan **orchestration ve choreography** farkını uzun cümlelerle özetleyen bir tablo kullanıyorum:

| Özellik | Orchestration 🧠 | Choreography 💃 |
|--------|------------------|-----------------|
| Akışı kim yönetir? | Orchestration yaklaşımında, tüm Saga akışı tek bir orkestratör bileşeni tarafından yönetilir; bu bileşen hangi servislerin hangi sırayla çağrılacağını, hangi adımın ne zaman başlayıp ne zaman biteceğini ve hata durumunda hangi telafi fonksiyonlarının devreye gireceğini merkezi olarak bilir ve uygular. | Choreography yaklaşımında, ortada merkezi bir beyin yoktur; her mikroservis belirli event’leri dinler, bu event’ler gerçekleştiğinde kendi işini yapar ve gerekirse yeni event’ler yayınlayarak diğer servislerin devreye girmesini sağlar, böylece akış sistemi oluşturan servislerin kolektif davranışıyla ortaya çıkar. |
| Bağımlılık yapısı | Orchestration modelinde, iş akışı orkestratöre oldukça bağımlıdır; yani yeni bir adım eklemek, bir adımı çıkarmak ya da akış sırasını değiştirmek istediğimde çoğu zaman bu merkezi bileşene dokunmam, onu deploy etmem ve test etmem gerekir, bu da onu sistemde önemli bir “bağımlılık noktası” haline getirir. | Choreography modelinde, servisler sadece dinledikleri ve ürettikleri event’lere bağımlıdır; yeni bir servis eklemek veya bir adım daha eklemek için genellikle sadece ilgili event’e subscribe olmak yeterli olduğundan, sistemin geneli üzerinde daha az etkisi olan, daha modüler ve gevşek bağlı bir tasarım elde ederim. |
| Okunabilirlik ve izlenebilirlik | Orchestration kullanırken, akış tek bir yerde tanımlandığı için, özellikle yeni başlayanlar veya ekip arkadaşlarım için Saga’nın genel mantığını anlamak çok daha kolay olur; gözle görülebilir, adım adım ilerleyen bir iş akışı diyagramını tek bir kod dosyasına bakarak zihnimde canlandırabilirim. | Choreography’de akış, birçok mikroservisin içinde dağınık halde yer aldığı için, tüm Süreci anlamak adına birden fazla servisin event handler’larını incelemem gerekir; bu durum güçlü observability, distributed tracing ve iyi bir dokümantasyon yoksa, özellikle canlı sistemde problemleri debug etmeyi zorlaştırabilir. |
| Genişletilebilirlik | Orchestration yaklaşımı, net bir akış sağlasa da, yeni bir adım eklerken veya var olan bir adımı yeniden sıralarken, çoğu zaman orchestrator’ın kodunu değiştirmemi ve bunu yeniden dağıtmamı gerektirir; bu da sık değişen iş kuralları olan ortamlarda ekstra efor demektir. | Choreography’de yeni bir iş adımı eklemek için genellikle sadece ilgili event’e abone olan yeni bir mikroservis veya mevcut servise yeni bir event handler eklemem yeterli olur; orchestrator’ı değiştirmek zorunda kalmadığım için, sistem doğal olarak daha genişletilebilir ve esnek bir yapıya kavuşur. |
| Risk ve tekil arıza noktası | Orchestration, yanlış tasarlanırsa, sistemde ciddi bir “single point of failure” yaratabilir; orchestrator bileşeni çökerse, Saga akışlarının tamamı duran, isteklere cevap veremeyen ve hata üreten bir duruma gelebilir, bu yüzden bu bileşeni yüksek erişilebilirlik ve yedeklilikle tasarlamak zorunda kalırım. | Choreography, merkezi bir bileşene bağlı olmadığı için tekil arıza noktası açısından daha avantajlı olsa da, event’lerin yanlış tasarlanması, sonsuz döngülere giren event zincirleri veya yanlış telafi akışları gibi riskler barındırır; bu nedenle “merkez yok” diye rehavete kapılmadan disiplinli bir event tasarımı yapmak zorunda olduğumu bilirim. |


---

#### 🧬 Orchestration ve Choreography Bir Arada Nasıl Kullanılır?

Benim mimari bakışımda, bu iki yaklaşım birbirinin düşmanı değil; çoğu zaman **hibrit bir model** benimsemek daha gerçekçi ve güçlü geliyor. ⚗️

- Bazı durumlarda, **üst seviye, kritik ve iş olarak net tanımlı Saga akışlarını orchestration ile yönetip**, bu akışların içinde yer alan alt adımların bir kısmını, mikroservislerin kendi aralarında event’ler üzerinden konuştuğu **choreography** ile çözmek, bana hem kontrol hem de esneklik sağlayan dengeli bir yaklaşım sunar.

- Örneğin, sipariş–ödeme–stok–kargo gibi “omurga” akışı orchestration ile yönetilirken, kullanıcıya bildirim gönderme, loglama, analitik event üretimi gibi **yan fakat önemli süreçleri** choreography ile, tamamen event tabanlı ve gevşek bağlı bir şekilde tasarlayabilirim.

- Ayrıca, sistemim büyüdükçe, başlangıçta orchestration ile kurduğum bazı akışların, zamanla event sayısı arttıkça ve servislere yeni davranışlar eklendikçe, **kademeli olarak choreography modeline evrilmesi** de oldukça doğal bir yol haritası olarak karşıma çıkar.

- Ben bu hibrit yaklaşımla, bir yandan **kritik iş süreçlerinde merkezi kontrolün sağladığı güven ve netlikten**, diğer yandan **event tabanlı mimarinin sağladığı esneklikten ve gevşek bağlılıktan** yararlanarak, her iki dünyanın da güçlü yanlarını bir arada kullanmayı hedeflerim.

- Kısacası, kendi mimari tercihimde, **“her yere tek bir model dayatmak” yerine, iş gereksinimine ve ekip olgunluğuna göre orchestration ve choreography’yi birlikte harmanlayan, pragmatik bir Saga yaklaşımını** daha olgun ve sürdürülebilir buluyorum.


---

#### 📨 Queue’nin Saga’daki Rolü

Saga pattern içinde queue, benim gözümde mikroservisleri birbirine zincirleyen görünmez ama inanılmaz güçlü bir “sinir sistemi” gibi çalışır; çünkü adımların sırasını, başarısını, tekrar deneme (retry) mantığını ve hatta telafi (compensation) akışlarını bile büyük ölçüde bu mesaj kuyrukları üzerinden koordine eder. 🧠📬 Bir sipariş senaryosunda, sipariş servisi doğrudan ödeme servisine bağlanmak yerine, “OrderCreated” mesajını kuyruğa bırakır; ödeme servisi ise bu kuyruğu dinleyerek mesajı kendi temposunda çeker, local transaction’ını çalıştırır ve sonucu yeni bir mesaj (örneğin “PaymentCompleted” veya “PaymentFailed”) olarak başka bir kuyruğa yazar; böylece servisler birbirine sıkı bağlı HTTP çağrılarıyla kilitlenmek yerine, queue sayesinde gevşek bağlı, dayanıklı ve asenkron bir akış içinde konuşur. Kuyruk, hem sistemdeki yük dalgalanmalarını yumuşatarak back pressure oluşturur, hem de mesajların kalıcı (durable) tutulması sayesinde, bir servis geçici olarak down bile olsa, ayakta kalan diğer servislerin süreci güvenle sürdürebilmesini sağlar; bu da Saga’nın doğal parçası olan eventual consistency modeline tam oturur: her adım kendi zamanında, kendi transaction’ında çalışır, kuyruğa bırakılan mesajlar ve tekrar denemelerle sistem yavaş yavaş tutarlı hale gelir. Ayrıca queue, telafi akışlarında da kritik rol oynar; bir adımda hata oluştuğunda, telafi edilmesi gereken önceki adımlar için yine uygun “compensation” mesajları kuyruklara bırakılır ve ilgili mikroservisler bu mesajları işleyerek, domain düzeyinde geri sarma (örneğin refund, stok iadesi, sipariş iptali) işlemlerini hayata geçirir; yani özetle, Saga’daki queue, hem iş akışını birbirine bağlayan, hem de tutarlılık, dayanıklılık ve esneklik üçgenini ayakta tutan, sessiz ama mimarinin bel kemiği niteliğinde bir yapı taşıdır. 💪

---

#### 🧪 Örnek Saga Orchestrator Akışı (Kod Gibi Düşünebileceğim Basit Bir Şablon)

Aşağıda, kafamda canlandırdığım basit bir **orchestration** örneğini, Java-benzeri bir pseudo kod şeklinde yazıyorum ve bu akışın Saga mantığını nasıl temsil ettiğini kısaca özetliyorum:

```java
public class OrderSagaOrchestrator {

    private final OrderService orderService;
    private final PaymentService paymentService;
    private final StockService stockService;
    private final ShippingService shippingService;

    public void startOrderSaga(CreateOrderRequest request) {
        Long orderId = null;
        try {
            // 1) Sipariş oluştur
            orderId = orderService.createOrder(request);

            // 2) Ödemeyi al
            paymentService.pay(orderId);

            // 3) Stok ayır
            stockService.reserve(orderId);

            // 4) Kargoyu başlat
            shippingService.startShipment(orderId);

        } catch (Exception ex) {
            // Hata durumunda telafi adımlarını ters sırayla çalıştır
            compensate(orderId);
            throw ex;
        }
    }

    private void compensate(Long orderId) {
        // Telafi (compensation) adımları
        try { shippingService.cancelShipment(orderId); } catch (Exception ignored) {}
        try { stockService.release(orderId); }        catch (Exception ignored) {}
        try { paymentService.refund(orderId); }      catch (Exception ignored) {}
        try { orderService.cancel(orderId); }        catch (Exception ignored) {}
    }
}
```

- Bu örnekte, OrderSagaOrchestrator, sipariş akışının tüm adımlarını merkezi olarak yöneten bir orchestrator görevi görür; sipariş oluşturma, ödeme alma, stok ayırma ve kargoyu başlatma işlemlerini sırasıyla çağırır, hata durumunda ise telafi adımlarını ters sırada devreye sokar.

- Her bir servis kendi içinde, kendi veritabanına karşı local transaction çalıştırır; yani createOrder, pay, reserve, startShipment gibi metotların her biri kendi DB transaction’ını kullanır, bu sayede veritabanı seviyesinde, her adım bağımsız olarak güvence altına alınır.

- compensate metodu, klasik veritabanı rollback’inden farklı olarak, mantıksal olarak terse çevirme (compensation) işlemlerini tetikler; cancelShipment, release, refund, cancel gibi metotlar, işin önceki adımlarını domain seviyesinde geri sarmaya çalışır.

- Bu kod parçası, benim için Saga mantığını somutlaştıran, orchestration tabanlı bir akış iskeleti gibidir; gerçek hayatta retry mekanizmaları, idempotency kontrolleri, loglama, event üretimi ve distributed tracing gibi birçok ek detay devreye girse de, omurga mantık bu basit şema üzerinden rahatlıkla anlaşılabilir.

- Böyle bir orchestrator yazarak, iş akışının tamamını tek bir yerde görebilme, hata noktalarını netleştirme ve telafi mantığını bilinçli şekilde tasarlama fırsatı bulurum; bu da gerçek üretim sistemlerinde bana hem özgüven hem de müdahale kolaylığı sağlar.

- Bu dokümanda, Saga design pattern, orchestration ve choreography üzerine, monolith ve klasik transaction dünyasından başlayarak, dağıtık tutarlılık, telafi işlemleri ve eventual consistency gibi kavramları, kendi bakış açımdan, uzun ama anlamlı cümlelerle toparlamaya çalıştım.

- Benim için bu konular, sadece teorik tasarım desenleri değil, aynı zamanda mikroservis mimarisine geçerken zihniyetimi dönüştüren, yazılım tasarımına daha “süreç” odaklı bakmamı sağlayan temel yapı taşları haline geldi. 💡🚀

---

##### Şematize 1. Orkestrasyon Tabanlı Saga (Merkezde “Saga Orchestrator” + MQ Var)

```text
                      ┌───────────────────────────┐
                      │       API Gateway        │
                      └────────────┬─────────────┘
                                   │  (1) HTTP/REST
                                   ▼
                      ┌───────────────────────────┐
                      │    Saga Orchestrator      │
                      └────────────┬──────────────┘
                                   │  (2) Command / Event
                                   ▼
                      ┌───────────────────────────┐
                      │   Message Broker / MQ     │
                      │ (Kafka / RabbitMQ / SQS)  │
                      └───────┬─────────┬────────┘
                              │         │
                   (3) Cmd/Event       (5) Cmd/Event
                              │         │
                ┌─────────────▼─┐     ┌─▼───────────────┐
                │  OrderService │     │ PaymentService  │
                └───────┬───────┘     └───────┬─────────┘
                        │ (4) Event/Reply      │ (6) Event/Reply
                        ▼                      ▼
                ┌─────────────┐        ┌──────────────┐
                │  Order DB   │        │ Payment DB   │
                └─────────────┘        └──────────────┘
                              ▲
                              │  (7) Cmd/Event (MQ üzerinden)
                ┌─────────────┴─────────────┐
                │     InventoryService      │
                └─────────────┬─────────────┘
                              │ (8) Event/Reply
                              ▼
                         ┌─────────┐
                         │ Inv DB  │
                         └─────────┘
```

- Kullanıcı sipariş verir → İstek API Gateway üzerinden gelir ve Saga Orchestrator’a yönlendirilir.

- Orchestrator, çoğu gerçek mimaride OrderService’e direkt HTTP ile değil, mesaj kuyruğu (Kafka topic, RabbitMQ exchange vb.) üzerinden “siparişi oluştur” komutunu yayınlar; OrderService bu komutu MQ’den dinleyip kendi lokal transaction’ını (Order DB) çalıştırır.

- OrderService, işlem başarılı olursa yine MQ üzerinden “OrderCreated” olayını/cevabını yayınlar, Orchestrator bu olayı dinleyerek bir sonraki adımı tetikler; başarısız olursa “OrderFailed” gibi bir olay yayılabilir ve saga burada sonlandırılabilir.

- Orchestrator, sıradaki adımda PaymentService’e yeni bir komut mesajı gönderir (“ödemeyi al”) ve PaymentService kendi transaction’ını (Payment DB) çalıştırır; sonuç yine bir event/cevap mesajı olarak kuyruğa yazılır (“PaymentCompleted” / “PaymentFailed”).

- Ödeme başarılıysa Orchestrator, bu kez MQ üzerinden InventoryService’e “stok düş” komutunu yollar; stok güncelleme de kendi lokal transaction’ı içinde yapılır, sonucunda “StockReserved” veya “StockReservationFailed” gibi olaylar yayınlanır.

- Eğer herhangi bir adımda hata gelirse Orchestrator, yine kuyruk üzerinden telafi komutları yayınlar:

- PaymentService için: “RefundPayment”

- OrderService için: “CancelOrder” gibi, her biri kendi telafi transaction’ını çalıştırır.

##### Özet:

>Burada bütün iş akışının beyni Orchestrator’dır, ama bu beyin servislere doğrudan bağlanmaz; komut ve olayları genellikle Kafka / RabbitMQ gibi bir mesaj kuyruğu üzerinden gönderip alır. Hangi adım hangi sırada çalışacak, hangi mesaj hangi servis tarafından tüketilecek, hata durumunda hangi telafi komutu yayınlanacak, hepsi Orchestrator’da toplanır; böylece akış merkezi ve okunaklıdır, ancak sistemdeki bağımlılık da doğal olarak bu merkezî bileşende yoğunlaşır (trade-off tam olarak buradadır).

---

##### Şematize 2. Koreografi (Choreography) Tabanlı Saga (Event-Driven Yaklaşım)

```text
      ┌──────────────────────┐          ┌──────────────────────┐
      │     OrderService     │          │    PaymentService    │
      └──────────┬───────────┘          └──────────┬───────────┘
                 │                                  │
 (1) OrderCreated event yayınlar                    │  (3) PaymentCompleted /
                 │                                  │      PaymentFailed event
                 ▼                                  │      yayınlar
         ┌──────────────────────┐                   │
         │   Event Bus / MQ     │◄──────────────────┘
         │ (Kafka / RabbitMQ)   │
         └───────┬──────────────┘
                 │   (2) Subscribe: OrderCreated
                 │
      ┌──────────▼───────────┐
      │   InventoryService   │
      └──────────┬───────────┘
                 │  (4) StockReserved /
                 │      StockReservationFailed event yayınlar
                 ▼
         ┌──────────────────────┐
         │   Event Bus / MQ     │
         └───────┬──────────────┘
                 │
      ┌──────────▼──────────────┐
      │   NotificationService   │
      └─────────────────────────┘
```

- OrderService, siparişi kendi lokal transaction’ı içinde başarıyla oluşturduğunda, başka kimseyi doğrudan çağırmaz; sadece Event Bus üzerine bir domain olayı yazar: OrderCreated. Yani “şunu yap” komutu vermez, “şu oldu” bilgisini sisteme duyurur.

- Event Bus (Kafka, RabbitMQ vb.) bu OrderCreated olayını, bu olaya abone olan tüm servislerin önüne düşürür. PaymentService de InventoryService de bu olayı dinleyebilir; kim nasıl tepki vereceğine kendi içinde karar verir.

- PaymentService, OrderCreated olayını yakaladığında, ödeme alma sürecini başlatır; kendi veritabanında lokal transaction’ını çalıştırır ve sonuca göre bir olay daha yayınlar: PaymentCompleted veya PaymentFailed. Yine kimseyi doğrudan çağırmaz, sonucu sistemin geri kalanına “olay” olarak bırakır.

- InventoryService, hem OrderCreated hem de ödeme ile ilgili olayları dinler. Ödeme başarılıysa siparişe ait stokları düşer, başarılı olursa StockReserved, başarısız olursa StockReservationFailed olayını yayınlar. Böylece stok yönetimi de tamamen lokal transaction ve olay bazlı yürür.

- NotificationService, bu olayların hiçbiriyle doğrudan entegre değildir; sadece Event Bus’ı dinler. OrderCreated, PaymentCompleted, StockReserved, PaymentFailed gibi olayları gördükçe kullanıcıya e-posta, SMS veya push bildirimi gönderir. Bu sayede bildirim mantığı da diğer servislerden gevşek bağlı kalır.

- Telafi (compensation) akışı:

- Ödeme başarısız olduğunda yayılan PaymentFailed olayı, OrderService tarafından dinlenebilir; OrderService bu olayı gördüğünde siparişi kendi veritabanında CANCELLED durumuna çeker. Böylece klasik “rollback” yerine, farklı bir yerel transaction ile telafi yapılmış olur.

- Stok ayırma başarısız olduğunda yayılan StockReservationFailed olayı ise PaymentService için tetikleyici olabilir; servis bu olayı yakalayıp ilgili sipariş ödemesi için RefundPayment benzeri bir süreç çalıştırır ve bunu da yeni bir event ile sisteme bildirir. Telafi mantığı da tamamen olay zinciri üzerinden akar.

##### Özet:

>Koreografi yaklaşımında ortada herkese emir veren bir Orchestrator yoktur; sistemin ritmini Event Bus üzerindeki olaylar belirler ve her servis sadece “duyduğu olaya” göre kendi adımını atar. Bu sayede servisler arası doğrudan bağımlılıklar azalır, mimari daha esnek ve evrilebilir hale gelir; ancak iş akışının tamamını anlamak ve bir sorunu uçtan uca debug etmek zorlaşır, çünkü senaryo tek bir merkezde değil, farklı servislerin yayınladığı ve tükettiği olayların birleşiminde ortaya çıkar.

---

