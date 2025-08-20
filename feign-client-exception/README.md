#### 🛡️ Feign Client Exception Yönetimi: Merkezi Hata Yakalama & Özelleştirilmiş Decoder 🚀🔥

Bugün Feign Client ile mikroservisler arası iletişimde exception handling yetkinliğimi geliştirdim 🙌. Amacım, öğrenciler servisinin kurs servisini tüketirken oluşabilecek tüm hataları kontrollü, okunabilir ve yönetilebilir şekilde ele almak 🧩.

##### 🔎 Neden yaptım?

Servisler arası çağrılarda 400, 500, 503 gibi hataların raw FeignException olarak gelmesi yönetimi zorlaştırıyor.

Merkezi bir hata yönetimi ile hem loglama hem kullanıcı mesajlarını standardize etmek istedim.

Gerçek dünya mikroservis senaryolarında hata toleransını ve sistem stabilitesini artırmak için gerekliydi.

##### 💡 Ne yaptım, ne ettim?

📌 CustomErrorDecoder ile FeignClient’in döndüğü hataları anlamlı özel exception’lara dönüştürdüm.

🛠️ FeignClientConfig üzerinden bu decoder’ı FeignClient’a entegre ettim.

🌐 GlobalExceptionHandler ile hem FeignException’ları hem genel exception’ları merkezi bir noktadan yakaladım ve HTTP response’larını kullanıcı dostu hale getirdim.

🎯 Artık öğrenciler servisi, kurs servisini tüketirken oluşan tüm hataları önceden belirlenmiş mantıkla yönetiyor, beklenmeyen durumlarda bile stabil çalışıyor.

✨ Kısacası: Exception handling’i öğrendim, merkezi ve özelleştirilmiş bir yapı kurdum, mikroservisler arası iletişimi güvenli ve profesyonel hale getirdim. 💪🔥

##### ------------------------

#### 🚀 Spring Boot Kurs Servisi: Dinamik Kurs Yönetimi & Değerlendirme Sistemi 🎯📚⭐

Bismillah diyerek 🙏 bugün Spring Boot tabanlı Kurs Servisi geliştirmemi tamamladım.
Bu çalışma ile kurs yönetimi, kaynak bazlı dinamik kontrol ve öğrenci değerlendirmeleri tek bir yapıda toplandı.

##### 🔎 Neden bu geliştirmeyi yaptım?

Gerçek hayatta eğitim platformları (Udemy, Coursera, Unacademy) farklı dinamiklere sahip.

Kullanıcıların derslere değerlendirme bırakabilmesi, sistemin değerini artırıyor.

Bir yazılımın anlamı, kullanıcıya fayda sunabildiği ölçüde büyür.

##### 💡 Ne elde ettim?

📂 CourseController ile REST tabanlı uç noktalar (GET, POST) sağladım.

🔄 CourseService ile courses.json verilerini otomatik yükleyip yönetebilen bir yapı kurdum.

🧑‍🎓 Course ve Rating DTO’ları ile ortalama puanlama ve dinamik yorum desteği ekledim.

⚡ Kaynak bazlı kontrol mekanizması (Udemy/Coursera/Unacademy) ile farklı platform senaryolarını destekledim.

##### 📌 Sonuç:
Artık elimde, dinamik kurs yönetimi ve değerlendirme sistemi barındıran, hem teknik olarak ölçeklenebilir hem de eğitim dünyasına uyarlanabilir güçlü bir Spring Boot servisi var.

##### ✨ Kısacası:
Veriyi yönettim, kullanıcıya değer kattım, yazılımı anlamlı hale getirdim.