## saga-orchestration

### 🏛️ Monolith’ten Saga Dünyasına Geçiş 🧩⚙️

Monolith yapılarda tüm işlemler genellikle aynı veritabanı ve aynı transaction context içinde çalıştığı için, hata durumunda rollback mekanizması devreye girer ve sistem tek hamlede eski tutarlı haline döner 🛡️💾; bu yapı ACID garantileri sayesinde oldukça deterministik ve öngörülebilir davranır. Ancak mikroservis mimarisine geçtiğimde artık her servisin kendi veritabanına sahip olması gerektiğinden, tek bir global transaction yönetmek hem zor hem de çoğu zaman gerçekçi olmaktan çıkar 🌐🔗. Saga design pattern tam bu noktada devreye girer ve uzun business flow’ları; sipariş 📦, ödeme 💳, stok 📚 ve kargo 🚚 gibi birbirinden bağımsız çalışan local transaction adımlarına böler. Eğer süreçte hata oluşursa klasik rollback yerine refundPayment 💸, releaseStock 🔄 veya cancelOrder ❌ gibi compensation (telafi) işlemleri çalıştırılır ve sistem teknik olarak değil ama iş mantığı seviyesinde geri sarılmaya çalışılır ⚖️🧠.

### 🧠 Orchestration Tabanlı Saga Mantığı 🎼📬

Orchestration yaklaşımında sürecin merkezinde bir Saga Orchestrator bulunur 🧠⚙️; bu yapı hangi servisin ne zaman çalışacağını, hangi event’in beklendiğini ve hata durumunda hangi compensation akışının tetikleneceğini merkezi olarak yönetir 🎯📑. Gerçek üretim ortamlarında orchestrator çoğu zaman servislerle direkt HTTP yerine Kafka 📨, RabbitMQ 🐇 veya SQS ☁️ gibi message broker sistemleri üzerinden haberleşir; yani servisler birbirine sıkı bağlı çağrılar yapmak yerine command ve event mesajlarıyla asenkron şekilde iletişim kurar 🔁📡. Böylece sistem daha gevşek bağlı 🤝, daha dayanıklı 🛠️ ve daha ölçeklenebilir 🚀 hale gelirken, eventual consistency mantığı sayesinde tüm servislerin aynı anda değil, zaman içinde tutarlı hale yaklaşması kabul edilir ⏳✅.

### 🚀 Saga’nın Mimari Etkisi ve Gerçek Dünya Yaklaşımı 🔍💡

Benim gözümde Saga sadece teknik bir pattern değil, mikroservis mimarisinde düşünme biçimini değiştiren ciddi bir zihniyet dönüşümüdür 🧩🧬; çünkü artık sistemi yalnızca sınıflar ve metodlar üzerinden değil, süreçler 📊, event akışları 📣, transaction sınırları 🔒 ve telafi senaryoları 🔄 üzerinden modellemeye başlarız. Orchestration yaklaşımı merkezi kontrol 🧭, okunabilirlik 📖 ve operasyonel müdahale kolaylığı 🛠️ sağlasa da, yanlış tasarlandığında orchestrator’ın aşırı büyümesi 📈, single point of failure oluşturması ⚠️ veya dağıtık monolith hissi yaratması gibi riskler de taşır 🏗️💥. Bu yüzden production seviyesindeki Saga mimarilerinde observability 👀, retry mekanizmaları 🔁, idempotency 🧷, distributed tracing 🛰️ ve queue tabanlı event yönetimi 📨 artık opsiyon değil, sistemin ayakta kalmasını sağlayan temel yapı taşları haline gelir 💪⚡.

### 🎼 Orchestration vs 💃 Choreography — Merkezi Zihin ile Dağıtık Davranışın Ayrımı 🧠⚙️

Orchestration yaklaşımında süreç, tek bir “karar verici zihin” 🧠🎯 tarafından baştan sona kontrol edilir; yani tüm iş akışı, hangi servisin ne zaman çalışacağı, hangi adımın önce ya da sonra geleceği ve hata durumunda hangi telafi adımlarının devreye gireceği gibi kritik kararlar merkezi bir yapı içinde tanımlanır ⚙️📜. Bu modelde akış daha okunabilir 📖 ve daha öngörülebilir 🔍 bir yapı sunar çünkü tüm hikâye tek bir noktada toplanır, ancak bu merkez büyüdükçe sistemin yükü artar 📈 ve tekil bir bağımlılık noktası (single point of coordination) oluşur ⚠️.

Choreography ise bunun tam tersine, hiçbir merkezi kontrol noktasının olmadığı, davranışların tamamen event’ler üzerinden şekillendiği bir dağıtık bilinç modelidir 🌐⚡; her mikroservis sadece kendi duyduğu olaylara tepki verir 📣 ve kendi sorumluluğunu yerine getirir, ardından yeni event’ler üreterek zinciri doğal akışında devam ettirir 🔁. Bu yapı daha gevşek bağlı 🤝, daha esnek 🚀 ve genişletilebilir 🧩 bir sistem kurar fakat iş akışının bütününü tek bir yerden görmek zorlaştığı için sistemin zihinsel modeli daha dağınık hale gelir 🧠🌫️; bu yüzden güçlü observability 👀, tracing 🛰️ ve disiplinli event tasarımı olmazsa süreç kontrol edilmesi zor bir karmaşıklığa dönüşebilir ⚠️.

### 🧭 Orchestration mı 💃 Choreography mi? — Doğru Modeli Seçme Mantığı ⚙️🧠

Orchestration yaklaşımı, iş akışının net bir sıraya bağlı olduğu 📑, adımların birbirine sıkı şekilde bağımlı ilerlediği 🔗 ve hata yönetiminin tek bir merkezden kontrollü biçimde yürütülmesi gerektiği durumlarda daha doğru bir seçimdir 🧠🎯; özellikle finansal işlemler 💳, sipariş akışları 📦, rezervasyon sistemleri 🎫 gibi “yanlış olursa telafisi kritik” olan süreçlerde, tüm hikâyeyi tek bir orchestrator üzerinden görmek ve yönetmek büyük bir kontrol avantajı sağlar ⚙️👀. Buna karşılık Choreography yaklaşımı, sistemin doğal olarak genişlediği 🌐, servislerin birbirinden bağımsız evrildiği 🚀 ve yeni davranışların mevcut akışı bozmadan eklenmesi gerektiği durumlarda daha uygun hale gelir 🧩; örneğin bildirim sistemleri 📣, loglama 📊, analitik akışlar 📈 veya kullanıcı etkileşim event’leri gibi gevşek bağlı ve yüksek esneklik isteyen yapılarda, event tabanlı dağıtık koordinasyon çok daha ölçeklenebilir ve sürdürülebilir bir yapı sunar ⚡🤝. Kısacası kontrol ve netlik gerekiyorsa orchestration 🧠🎼, esneklik ve evrim gerekiyorsa choreography 💃📡 tercih edilir; çoğu gerçek sistemde ise bu iki yaklaşımın dengeli bir hibrit kullanımı, hem yönetilebilirlik hem de ölçeklenebilirlik açısından en sağlıklı mimari noktayı oluşturur ⚖️🚀.

### 🧠⚙️Terimler 📚

***Monolith 🏛️***  
Monolith mimari, bir uygulamanın tüm iş mantığının, veri katmanının ve servis davranışlarının tek bir bütün halinde çalıştığı yapıdır ⚙️💾; her bileşen aynı yaşam döngüsüne bağlı olduğu için bir noktadaki değişiklik tüm sistemi doğrudan etkiler 📉, bu da sistemi hem güçlü hem de merkezi yapısı nedeniyle kırılgan hale getirir 🧱.

***Saga Design Pattern 🧩***  
Saga, dağıtık sistemlerde büyük ve tek parça transaction yerine, iş akışını küçük, bağımsız ve yönetilebilir adımlara bölerek ilerleyen bir yaklaşımdır 🔁; hata oluştuğunda ise “rollback” yerine iş mantığıyla yazılmış telafi (compensation) adımları devreye girer ⚖️💡 ve sistem dengeye geri getirilir.

***Transaction 💾***  
Transaction, veritabanında yapılan birden fazla işlemin tek bir bütün gibi ele alınmasını sağlar 🧠; sistem ya tüm adımları birlikte başarılı kabul eder ya da hiçbiri gerçekleşmemiş gibi davranır ⚙️, böylece veri tutarlılığı korunur 📊.

***Rollback 🔄***  
Rollback, bir transaction sırasında hata oluştuğunda yapılan tüm değişiklikleri geri alarak veritabanını önceki tutarlı haline döndürme mekanizmasıdır 🛡️; amaç hatayı silmek değil, sistemin hiç bozulmamış gibi eski haline dönmesini sağlamaktır ⏪.

***ACID ⚖️***  
ACID, veritabanı transaction’larının güvenilirliğini tanımlayan temel prensipler bütünüdür 📊; Atomicity 🧩, Consistency 📐, Isolation 🔐 ve Durability 💾 sayesinde işlemler güvenli, tutarlı ve kalıcı şekilde yönetilir.

***Microservice (Mikroservis) 🌐***  
Mikroservis mimarisi, büyük bir sistemi küçük, bağımsız ve kendi veritabanına sahip parçalara ayırarak her bir parçanın kendi yaşam döngüsünü yönetmesini sağlar 🚀; bu yaklaşım ölçeklenebilirliği artırırken sistem karmaşıklığını farklı bir boyuta taşır.

***Local Transaction 🧠***  
Local transaction, her mikroservisin yalnızca kendi veritabanı sınırları içinde gerçekleştirdiği işlem bütünüdür ⚙️📦; dış sistemleri doğrudan etkilemez ve sadece kendi veri tutarlılığını garanti eder.

***Compensation (Telafi) 🔁***  
Compensation, dağıtık sistemlerde hatalı bir süreci geri almak yerine, önceki adımların etkisini iş mantığıyla dengeleyen ters operasyonların çalıştırılmasıdır ⚖️; bu yaklaşım teknik değil, tamamen domain seviyesinde bir geri alma stratejisidir.

***Business Flow 📊***  
Business flow, bir kullanıcının hedefe ulaşmak için sistem içinde izlediği adımların bütünsel ve anlamlı zinciridir 🧭; önemli olan tek tek işlemler değil, bu işlemlerin oluşturduğu uçtan uca hikâyedir.

***RefundPayment 💸***  
RefundPayment, daha önce gerçekleşmiş bir ödeme işleminin iş mantığı çerçevesinde geri iade edilmesidir 💳; sadece para iadesi değil, sistemsel dengenin yeniden kurulması anlamına gelir.

***ReleaseStock 📦***  
ReleaseStock, daha önce rezerve edilmiş veya düşülmüş stokların tekrar sisteme kazandırılmasıdır 🔄; bu işlem fiziksel değil, tamamen mantıksal envanter düzeltmesidir.

***CancelOrder ❌***  
CancelOrder, oluşturulmuş bir siparişin sistem tarafından geçersiz hale getirilerek iş akışından çıkarılmasıdır 🧾; yalnızca silme değil, sürecin anlamlı şekilde sonlandırılmasıdır.

***Orchestration 🎼***  
Orchestration, mikroservislerde iş akışının merkezi bir kontrol noktası tarafından yönetildiği yaklaşımdır 🧠; tüm adımlar belirli bir sıraya göre bu merkez tarafından koordine edilir.

***Saga Orchestrator 🧠***  
Saga Orchestrator, dağıtık iş akışının karar verici merkezidir 🎯; hangi servisin ne zaman çalışacağına ve hata durumunda hangi telafilerin devreye gireceğine karar verir.

***Message Broker 📬***  
Message broker, mikroservisler arasında doğrudan bağımlılık kurmadan mesajların taşınmasını sağlayan ara katmandır ⚡; sistemin asenkron iletişim siniri gibi çalışır.

***Kafka 🚀***  
Kafka, yüksek hacimli event akışlarını gerçek zamanlı işleyebilen dağıtık bir veri akış platformudur 🌊; veriyi sadece taşımakla kalmaz, sürekli akan bir olay akışı olarak işler.

***RabbitMQ 🐇***  
RabbitMQ, mesajları kuyruk mantığıyla güvenli şekilde ileten bir mesajlaşma sistemidir 📥; mesajlar kaybolmadan sırayla doğru servis tarafından işlenir.

***SQS ☁️***  
SQS, AWS tarafından sunulan yönetilen mesaj kuyruğu servisidir 🌍; ölçeklenebilir ve güvenli şekilde servisler arası mesaj iletimini sağlar.

***Command 📤***  
Command, bir servise doğrudan “şunu yap” anlamı taşıyan emir mesajıdır ⚙️; amacı veri taşımak değil, aksiyon başlatmaktır.

***Event 📣***  
Event, sistemde gerçekleşmiş bir gerçeği temsil eder 🧠; “ne yapılmalı” değil, “ne oldu” bilgisini taşır ve diğer servisleri tetikler.

***Asenkron ⏳***  
Asenkron yapı, işlemlerin birbirini beklemeden bağımsız şekilde ilerlediği iletişim modelidir 🔁; sistem bloklanmadan çalışır ve akış önceliklidir.

***Eventual Consistency ⌛***  
Eventual consistency, verilerin anında değil zaman içinde tüm sistemde tutarlı hale gelmesini kabul eden veri modelidir 📊; nihai doğruluk hedeflenir.

***HTTP 🌍***  
HTTP, servislerin internet üzerinden istek–yanıt mantığıyla iletişim kurmasını sağlayan protokoldür 📡.

***Distributed System 🌐***  
Distributed system, birden fazla bağımsız servisin ağ üzerinden birlikte çalışarak tek sistem gibi davranmasını sağlayan mimaridir 🤝.

***Retry 🔁***  
Retry, başarısız işlemlerin belirli kurallarla tekrar denenmesidir ⚠️; çünkü dağıtık sistemlerde hata olağan bir durumdur.

***Idempotency 🧷***  
Idempotency, bir işlemin kaç kez yapılırsa yapılsın aynı sonucu üretmesini garanti eder 🔐; özellikle retry mekanizmalarında kritik rol oynar.

***Observability 👀***  
Observability, sistemin iç durumunun log, metric ve trace verileri üzerinden dışarıdan anlaşılabilmesidir 📊.

***Distributed Tracing 🛰️***  
Distributed tracing, bir isteğin mikroservisler arasındaki tüm yolculuğunu uçtan uca izlemeyi sağlar 🔍.

***Queue 📥***  
Queue, mesajların sıralı, güvenli ve kaybolmadan işlenmesini sağlayan veri yapısıdır 📬.

***Event-driven ⚡***  
Event-driven mimari, sistem davranışlarının olaylar üzerinden tetiklendiği yapıdır 🌐; servisler doğrudan değil event’ler üzerinden haberleşir.