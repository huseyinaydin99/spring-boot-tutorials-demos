### 🚀 Kafka Streams Transaction & Fraud Detection Projesi

Bu proje kapsamında gerçek zamanlı veri akışını (stream processing) temel alarak işlem verilerini Kafka üzerinden üretip işleyen ve analiz eden bir yapı geliştirdim. Amacım yalnızca veri taşımak değil, bu veriyi anlık olarak anlamlandırmak ve davranış analizi yapabilecek bir sistem kurmaktı ⚙️. Transaction verileri kullanıcı, işlem tipi, lokasyon ve ürün detaylarıyla birlikte üretiliyor ve Kafka Streams ile anlık olarak işleniyor. Böylece veri sadece saklanan bir yapı değil, yaşayan ve sürekli anlam üreten bir akış haline geliyor 🔥.

Stream tarafında gelen verileri çeşitli operasyonlarla dönüştürerek anlamlı hale getiriyorum. Filter, map, flatMap, branch gibi işlemlerle akışı şekillendirirken, groupBy ve aggregate gibi yapılarla veri üzerinde birikimli analizler yapıyorum 📊. Özellikle windowing yaklaşımı ile verileri 10 saniyelik zaman dilimlerine bölerek kullanıcı davranışlarını kısa zaman aralıklarında analiz ediyorum ⏱️. Bu sayede sistem, sadece toplam veriye değil zaman bazlı davranış değişimlerine de odaklanabiliyor.

Fraud detection kısmında ise belirli eşik değerleri üzerinden anomali tespiti yapıyorum 🚨. Örneğin bir kullanıcı 10 saniyelik pencere içinde belirli bir sayının üzerinde işlem yaparsa sistem bunu şüpheli davranış olarak işaretliyor. Peek yapısı ile sadece gözlem ve loglama gerçekleştirirken, asıl veri akışı hiçbir şekilde bozulmadan devam ediyor. Böylece sistem hem gözlemlenebilir hem de yan etkisiz bir yapıda çalışıyor 🧠.

---

#### 📊 Sistem Özeti

| Katman | Açıklama |
|--------|----------|
| 🚀 Veri Üretimi | Rastgele transaction verileri üretilerek Kafka topic’ine gönderilir |
| ⚙️ Stream Processing | Kafka Streams ile veriler filtrelenir, gruplanır ve dönüştürülür |
| 📊 Analitik Katman | Windowing ve aggregate işlemleri ile kullanıcı ve işlem bazlı analiz yapılır |
| 🚨 Fraud Detection | Belirli eşik değerleri aşan davranışlar şüpheli olarak işaretlenir |

---

#### 🧩 Kullanılan Kafka Streams Operasyonları

- `filter()` 🚨 → Belirli koşullara uyan transaction’ları seçerek sistemde sadece anlamlı verilerin ilerlemesini sağlar ve gereksiz yükü azaltır
- `map()` 🔄 → Key-value dönüşümü yaparak veri yapısını farklı bir perspektife taşır ve analiz edilebilir hale getirir
- `flatMap()` 📦 → Tek bir transaction’ı birden fazla alt öğeye bölerek detay seviyesinde analiz yapılmasını sağlar
- `branch()` 🌿 → Akışları koşullara göre farklı kollara ayırarak debit ve credit gibi farklı işlem türlerini ayrıştırır
- `groupBy()` 👤 → Veriyi kullanıcı veya özellik bazında gruplayarak toplu analiz yapılmasına imkan tanır
- `aggregate()` 💰 → Gruplanmış veriler üzerinde birikimli hesaplamalar yaparak toplam değer üretir
- `windowedBy()` ⏱️ → Veriyi zaman dilimlerine bölerek davranışların zaman bazlı analiz edilmesini sağlar
- `peek()` 👀 → Stream’i kesmeden sadece gözlemleme ve loglama yaparak debug ve monitoring imkanı sunar

---