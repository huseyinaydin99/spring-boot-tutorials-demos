# spring-boot-tutorials-demos
Bir ton videolu kurs izliyorum. Kendimi çok geliştirmek istiyorum. Gelişmek için bir ton örnek yapıyorum. Diğer insanların kodlarını inceliyorum. Bütün yaptığım kodları buraya commit'leyeceğim. 

![Spring Boot Java](spring-boot-java.jpg "Spring Boot Java")

##### Spring Nedir?
Spring nedir?
Spring Framework nedir?

Birden fazla tanım yapmak istiyorum.

Tanım 1: Spring, kurumsal üretime hazır uygulama oluşturmak için oluşturulmuş bir Java web çerçevesidir.

Tanım 2: Spring, Spring Source topluluğu tarafından Java programcıları için üretilmiş açık kaynak kodlu çeşitli yazılım çözümlerini içinde barındıran, dev bir ekosistem ve framework / çatıdır.

Bir Java kurumsal uygulaması oluşturduğumuzda bir çok konfigürasyon yapmamız gerekiyor, jar’lar eklememiz gerekiyor ve ayrıca veri tabanı desteğini de eklememiz gerekiyor.
Spring, birçok şeyi yapmamıza izin verecek Java web çerçevesidir. Spring, çeşitli konfigürasyonlar için bir grup modül sağlar, bu modülleri uygulamamızı oluşturmak için kullanabiliriz. Ancak tüm bu modülleri kullanmak için, uygulamayı kurmak ve çalıştırmak için birçok yapılandırma yapmamız gerekiyor.
Uzun lafın kısası, Spring çerçevesi üretime hazır uygulama oluşturmamıza yardımcı oluyor, ancak çok fazla yapılandırma yapmamız gerekiyor.

Spring’in ilk çıkış amacı projenin katmanları arası bağımsızlığı yahut gevşek bağlar kurma amaçlıydı. Daha sonradan dev bir ekosistem haline geldi. Spring yokken EJB(Enterprice Java Bean) vardı. Ancak EJB’nin kullanım şekli çok zordu. Hiç pratik değildi ve insanlar kullanmakta zorlanıyorlardı. EJB çok güçlü olmasına rağmen kullanımı çok zordu. Daha sonra Spring çıktı. Spring EJB’ye göre oldukça kolay konfigurasyon ve kullanım şekli sunuyordu. Kalite ve güç olarak EJB kadar iyiydi. Hem kaliteli hemde kolay kullanım sunduğu için insanlar Spring’i tercih ettiler ve Java dünyasının Defacto Standartı oluverdi.

Java Dünyasında Standart nedir?

Standart, Java’nın sahibi olan Oracle firması tarafından üretilmiş orijinal Java alt teknolojileridir.

Java Dünyasında Defacto Standart nedir?

Java’nın sahibi olan Oracle firması tarafından üretilmeyip farklı firmalar tarafından üretilip Standart olan teknolojiden bile çok fazla piyasada tutulup halk tarafından kabul gören teknolojiye Java dünyasında Defacto Standart deniliyor.

Spring Boot nedir?

Spring Boot, Spring Team tarafından hızlı ve kolay bir şekilde Spring tabanlı uygulamalar oluşturmak için sunulan bir araçtır. Sıfırdan otomatik yapılandırma / konfigurasyon desteği sağlar. Konfigürasyona odaklanmak yerine sadece gerçek iş mantığına odaklanabiliriz.
Spring boot bir çerçeve değil, Spring tabanlı uygulamalar oluşturmak için bir araç veya uzantıdır. Spring Boot dahili olarak Spring’i kullanır. Yani aslında bildiğimiz Spring Framework’ün güncellenmiş / modernize edilmiş / otomatize edilmiş / XML konfigurasyonlarından arındırılmış ve notasyon bazlı hale getirilmiş pratik halidir.


Neden Spring Boot kullanmalıyız?

Hızlı uygulama geliştirme ve yayına alma / deploying.
Bağımlılıkları yönetme / dependency management.
Otomatik yapılandırma desteği / auto configuration.
Dağıtık sunucular için destek / disturbuted server support.
Mikro hizmetler / Microservices oluşturma desteği / microservices creating support.
Invertion of Control / IoC / Kontrolün Tersine Çevrilmesi ve Bağımlılık Enjeksiyonu / Bağımlılıkların Enjekte Edilmesi / Dependency Injection :

Tipik Java uygulamalarında geliştirici olarak Java nesneleri new’leyerek kendimiz elle / manuel olarak yaratırız. Ancak kurumsal projelerde uygulamalarla çalışırken bu iyi bir fikir değil. Çünkü nesneleri elle yaratmak, yok etmek ve yaşam döngülerini yönetmek kolay değil. Böylece nesneleri elle yaratmak yerine, nesnelerin yaşam döngüsünü yönetmesi için çerçeveye / framwork’e denetim veriyoruz. Buna IoC(Inversion of Control) kontrolün tersine çevrilmesi yani nesnelerin kontrolünün Spring’in kabına bırakılması da denilebilir.

Spring Container ve yönetilen nesneler temsili resim:


IoC(kontrolü tersine çevirme)’yi kullanmak için, DI((Dependency Injection)bağımlılık enjeksiyonunu) kullanmamız gerekir; bu, new anahtar kelimesini kullanmadan Java nesneleri oluşturmamıza izin verecek bir tasarım modelidir.
Spring Boot uygulaması çalıştığında, Spring’in bir fabrika nesnesi veya konteyneri vardır, bizim için Bean(fasulye / kahve çekirdeği / aslında Spring tarafından yönetilen Java nesnesi) oluşturur, daha sonra belirli bir Bean(Spring’in yönettiği Java nesnesi) istediğimizde, önce konteyneri kontrol eder, eğer varsa, daha önce oluşturulmuş nesnenin bellek referansını yani bellek adresini verir, aksi takdirde bizim için yeni nesneler yaratır.


Spring Boot projesini nasıl oluşturulur?

Spring ekibi, Spring Boot başlangıç ​​projesi oluşturmak için çevrimiçi bir web aracı sağlar. Spring başlatıcı aracını bu URL’de bulabilirsiniz https://start.spring.io/
Spring boot projesi oluşturmak için tek yapmanız gereken proje detaylarını, Spring Boot sürümünü girmek ve bağımlılıkları eklemek ve generate / üret butonuna basmak.
