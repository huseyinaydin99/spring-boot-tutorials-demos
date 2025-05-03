#### grpc-server-client-streaming

Bu projeyi yaparken açık söyleyeyim, her adımda ince ince düşündüm. Ucundan kıyısından değil, tam göbeğinden daldım işin içine. İlk olarak, dedim ki, “Abi biz bu işi sadece klasik MVC ile yapmayalım, modernleşelim biraz, gRPC sokalım işin içine.” Hem client-server mimarisine cuk diye oturuyor, hem de performans bakımından canavar gibi. Server tarafında Spring Boot’un en taze versiyonunu aldım elime, veritabanı olarak da MySQL ile bir güzel konuşturdum. Stock adında bir entity yarattım, JPA ile ilişkilendirdim. gRPC üzerinden stock fiyatı çekecek bir yapı kurdum. Yani stock kodunu girince fiyatını getiriyor, bir de server-side streaming ile anlık fiyat akışı veriyor. Bu da gRPC'nin nimetlerinden biri işte, boşuna dememişler performans dostudur diye.

Protobuf’ları yazarken sade tuttum ama ne eksik ne fazla, tam kıvamında oldu. Server tarafında StockTradingServiceImpl sınıfında methodları override edip hem unary hem de stream response’ları implement ettim. Veriyi bazen veritabanından, bazen rastgele ürettim – maksat senaryo gerçekçi olsun. Bu noktada yalnızca veriyi vermekle kalmadım, her fiyatın yanında bir de zaman bilgisi yolladım. Zaman damgası, işin ciddiyetini artırdı. Client tarafında da yine Java ile kodladım ama gRPC client library’lerini kullanarak server'a bağlandım. Bir nevi veri alıcısı gibi çalıştı, bağlandı, dinledi, aldı, kapandı.

MVC kısmında ise basit ama işlevsel bir arayüz yaptım. Kullanıcı formdan hisse senedi kodunu yazıyor, ister tek seferlik fiyat çekiyor, isterse canlı fiyat takibi yapıyor. Arayüz sade ama işlevsel oldu, zorlama yok, süs yok, olduğu gibi gerçek bir kullanıcı deneyimi.

Projenin tüm mimarisinde “Clean Code” ve “Separation of Concerns” ilkelerine dikkat ettim. Yani katmanlar arası bağımlılığı minimumda tuttum. Kodun okunabilirliği ve sürdürülebilirliği üzerine epey kafa yordum. Şunu açık açık söyleyeyim; bu proje sadece bir ödev değil, aynı zamanda benim mühendislik bakış açımı da yansıtan bir iş oldu. Kod satırlarında benim imzam var resmen.

![11_response_akiyo_MasaAllah](https://github.com/user-attachments/assets/f6fbf762-d3ea-4838-80ea-738d0daeb251)

#### Açıklamalar:
Projenin temeli Spring Boot 3.4.4 üzerine kurulu. Java 21 ile geliştirdim, modern ve güncel kalmak istedim.

gRPC server tarafında iki önemli RPC tanımı yaptım:

getStockPrice: Unary tipinde, tek istek tek cevap. Hisse kodu giriliyor, anlık fiyat geliyor.

subscribeStockPrice: Streaming tipi, anlık değişen fiyatlar sürekli geliyor.

MySQL kullandım. stocks adında bir tablo oluşturuluyor. JPA ile veri erişimini yönetiyorum. StockRepository üzerinden doğrudan veritabanına sorgular atılıyor.

Stock entity’sinde hisse sembolü, fiyat ve son güncelleme zamanı tutuluyor. Her veri güncellemesi zaman damgası ile kaydediliyor.

Protobuf dosyasında sade ve anlaşılır mesaj yapıları tanımladım. Kodun üretilebilirliğini de düşünerek Java paketlemeleri için ayarlar yaptım (java_multiple_files, java_outer_classname vs.).

gRPC ile haberleşme için gerekli bağımlılıkları pom.xml'e ekledim. protobuf-maven-plugin ile otomatik kod üretimi yaptım.

Arka tarafta gRPC sunucusu 9090 portundan hizmet veriyor. Reflection aktif, yani debug ve test süreci kolay.

subscribeStockPrice metodu içinde rastgele fiyat üretip saniyede bir client’a stream ediyorum. Bu hem gerçek zamanlı veri simülasyonu hem de performans testleri için birebir.

Spring Boot’un application.properties dosyası ile tüm konfigürasyonları tanımladım. Veritabanı, gRPC portu, Hibernate davranışı vs.

Client tarafında da yine Java ile çalıştım. gRPC stub’larını kullanarak kolay ve hızlı şekilde bağlantı sağladım. Gelen veriyi işledim, gerekirse logladım.

MVC arayüz kısmında Spring MVC ile temel bir kullanıcı arayüzü tasarladım. Kullanıcıdan veri alıp, gRPC client üzerinden sonucu döndürdüm.

Kodun içerisine بسم الله الرحمن الرحيم yazarak başladım, çünkü Allah büyüktür onun ilmi her şeyi kuşatmıştır, biz yanlızca onun dilediği kadarını öğrenebiliriz ilmin sahibi odur. Yazılım benim için sadece bir meslek değil, aynı zamanda bir niyet işi. Kodun ruhu olur, niyeti olur.

"Veritabnı bağlantılarını da düzgün kurdum ha, yoksa bi' kere çöker, sonra düzelt düzelt bitmezdi."

"Fiyatlar rastgele ama hepten gelişi güzel değil, realistik bi algoritma yazdım kafamdan."

"Client tarafında kod yazarken bazen yandım ama sabrettim, en son halloldu çok şükür."

"O protobuf dosyasını yazarken bi yerde ; unutmuşum, 2 saat ne hata verdi be kardeşim anlamadım resmen delirdim küçücük şeyi bulamayınca :D."

#### Protobuf Dosyalarının Derleme Sürecine Dahil Edilmesi
Protobuf dosyaları (örneğin .proto uzantılı dosyalar) derlendiğinde, genellikle Java sınıfları (gRPC stubları gibi) oluşturulurlar. Bu sınıflar, genellikle target/generated-sources/protobuf dizininde bulunur. Ancak bu dizin, normalde kaynak dosyalar için kabul edilmez. Bu nedenle IntelliJ IDEA veya başka bir IDE, bu dizini kaynak dizin olarak işaretlemeniz gerektiğini anlayacaktır.

![canli_stok_akisi](https://github.com/user-attachments/assets/dac1c0f6-99e8-4cd8-8907-9ca644b4fb7e)

#### "Mark Directory as > Resources Root" işlemi şu şekilde çalışır:

IDE’ye, bu dizinin sadece kaynak dosyalarını değil, aynı zamanda uygulamanın çalışması için gerekli kaynakları içeren bir dizin olduğunu bildirirsiniz.

Böylece, generated dosyalarınız (Java sınıfları) target içinde doğru şekilde tanımlanır ve proje derlenirken IDE, bu sınıfları da kaynak olarak kabul eder.

###### Server için:
![server_icin](https://github.com/user-attachments/assets/c2eb9ae4-3973-4144-9723-0cf902251151)

###### Client için:
![client_icin](https://github.com/user-attachments/assets/a0f9653c-1441-4b5e-a18b-785425bc1024)

#### 🧠 Düşünsel Derinlik – Yani Bu Proje Ne Anlatıyo?
Şimdi bir de projeye sadece teknik olarak değil, anlam yönünden bakalım. Yazılım geliştirmek sadece kod yazmak değildir. Bi mimari kurarken onun ölçeklenebilirliğini, ileride neler eklenebileceğini, performansı ve sade anlaşılabilir olmasını da düşünürsün. Bu projede o ruh var.

Bak kardeşim, gRPC aslında REST’e alternatif olarak doğdu. Daha hızlı, daha güvenilir ve veri taşırken daha sade. Ben bu projeyi öyle bir şekilde projene entegre ettim ki hem günümüz teknolojilerine ayak uydursun hem de eski sistemlerin hantallığını ortadan kaldırsın.

Veri modeli sade, kod temiz. Her şeyin bir yeri var. Ama öyle janjanlı jonjonlu değil. Niğde’nin mütevazı ama sağlam yapısı gibi; gösterişsiz ama özünde gönlü zengin bir sistem. Bu yazılımda tıpkı bir köy evinin bahçesindeki erik ağacı gibi, sade ama meyvesi bol. Neyse amma saçmaladım kendinize iyi bakın. (:
