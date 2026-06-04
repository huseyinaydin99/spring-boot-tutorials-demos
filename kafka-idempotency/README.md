## 🚀 Kafka Idempotency Pattern

### Kafka'da Idempotency Nedir?

> Kafka'da idempotency, aynı mesajın üretici (producer) tarafından ağ hataları veya yeniden denemeler nedeniyle birden fazla kez gönderilse bile broker'a yalnızca bir kez yazılmasını sağlayan mekanizmadır. enable.idempotence=true ayarıyla etkinleştirilir ve duplicate (tekrarlanan) kayıtların oluşmasını engeller.

### 📌 Proje Hakkında

Bu proje, Apache Kafka kullanan mikroservis mimarisinde **Idempotency Pattern** uygulamasını göstermek amacıyla geliştirilmiştir. Amaç, aynı mesaj Kafka tarafından birden fazla kez teslim edilse bile ödeme işleminin yalnızca bir kez gerçekleşmesini sağlamaktır. 🛡️

Order Service tarafından oluşturulan siparişler Kafka üzerinden Payment Service'e iletilir. Payment Service ödeme kaydını oluşturduktan sonra bilinçli olarak hata üretir ve mesaj yeniden işlenmeye çalışılır. Ancak veritabanındaki benzersiz `requestId` kontrolü sayesinde aynı ödeme ikinci kez oluşturulamaz. Böylece veri tutarlılığı korunur ve mükerrer işlemler engellenir. 🔄

---

### 🏗️ Kullanılan Teknolojiler

| Teknoloji       | Amaç                   |
| --------------- | ---------------------- |
| Spring Boot     | Mikroservis geliştirme |
| Apache Kafka    | Asenkron mesajlaşma    |
| Spring Data JPA | Veri erişimi           |
| Hibernate       | ORM                    |
| MySQL           | Veri saklama           |
| JSON Serializer | Mesaj serileştirme     |

---

### Listener tekrar tetiklenir mi? 🔄

Evet, Kafka tarafında aynı mesaj tekrar gönderilirse (aynı event yeniden publish edilirse ya da retry olursa) consumer bunu tekrar alır ve listener yeniden tetiklenir ⚙️; Kafka “bu mesaj daha önce işlendi mi?” bilgisini kendiliğinden tutmaz, bunu idempotency 🛡️ veya offset mantığı 📍 ile sen yönetirsin.

### Bunu nasıl yönettim? 🧠

Bunu requestId üzerinde veritabanı unique constraint 🗄️ + exception handling ⚠️ + manuel ack ✍️ kombinasyonuyla yönettim; yani aynı event tekrar gelirse DataIntegrityViolationException fırlayıp işleme girmiyor ve offset commit edilerek mesaj tekrar işlenmeyecek şekilde tüketim noktası sabitleniyor 📌.

### acknowledgment.acknowledge() ne yapar? 📡

acknowledgment.acknowledge(); Kafka’ya mesajın başarıyla işlendiğini bildirerek offset’i ilerletmek için kullanılır ➡️; böylece aynı mesaj tekrar gelse bile consumer’ın kaldığı yer doğru şekilde korunur ve yeniden işleme kontrol altında tutulur 🛡️.

Evet, özünde şunu yapar: “bu mesajı işledim, offset’i ilerlet; servis yeniden başlasa bile aynı mesajı tekrar verme, kaldığı yerden devam et” bilgisini Kafka’ya bildirir 🔁📍.

Ben bu mesajı işledim sunucu kapanıp açılırsa eğer bana aynı mesajları almadığımı zannederek tekrar gönderme offset'i bil kaldığın yeri bil haddi bil lan ⚡

Kullanılmazsa offset commit edilmediği için Kafka o mesajı “işlenmedi” sayar ❗ ve consumer yeniden başladığında aynı mesajı tekrar tekrar gönderir 🔄 (en az bir kez teslim garantisi nedeniyle duplicate processing oluşur) ⚠️

---

### 🔄 Akış

1. 🛒 Order Service siparişi alır ve benzersiz bir `requestId` üretir.
2. 📤 Sipariş olayı Kafka üzerindeki `ORDER_TOPIC` konusuna gönderilir.
3. 📥 Payment Service mesajı tüketerek ödeme kaydı oluşturur.
4. ⚠️ Kontrollü hata senaryosu nedeniyle mesaj tekrar işlenmeye çalışılır.
5. 🛡️ `requestId` alanındaki unique constraint duplicate ödeme oluşmasını engeller.
6. ✅ Aynı mesaj tekrar gelse bile ödeme yalnızca bir kez kaydedilir.

---

### ✨ Öne Çıkan Noktalar

* 🛡️ Veritabanı seviyesinde idempotency uygulanmıştır.
* 🔄 Kafka'nın yeniden teslim ettiği mesajlar güvenli şekilde yönetilmiştir.
* ⚡ Manuel offset yönetimi kullanılarak hata senaryoları test edilmiştir.
* 📦 Mikroservisler arasında asenkron ve gevşek bağlı iletişim sağlanmıştır.
* 🎯 Dağıtık sistemlerde veri tutarlılığını koruyan gerçek bir kullanım senaryosu modellenmiştir.

---

> Mesaj birden fazla kez gelebilir, ancak ödeme yalnızca bir kez gerçekleşmelidir.
