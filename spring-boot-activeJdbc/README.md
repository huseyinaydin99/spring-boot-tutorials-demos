# spring-activeJdbc

ActiveJDBC, Java için Active Record tasarım desenini takip eden bir ORM kütüphanesidir ve karmaşık yapılandırmalar gerektirmeden veri tabanı işlemlerini hızlı ve sade bir şekilde gerçekleştirmek için kullanılır.
Active Record tasarım deseninde her veri tabanı kaydı bir nesneye eşlenir. Çok sayıda kayıt olduğunda her biri için ayrı nesne oluşur.
Active Record deseninde iş kuralları ve mantığı doğrudan Entity (varlık) sınıfının içinde yer alır. Böylece az kodla hızlı ve etkili işlemler gerçekleştirilir.
Active Record genellikle küçük projeler için daha uygundur, büyük veya karmaşık projelerde sınırlamalar yaratabilir.

Bu projede, MySQL veritabanı ile çalışan basit bir CRUD uygulaması geliştirdim. İlk olarak, ApplicationFilter sınıfıyla veritabanı bağlantılarını yönetip işlemlerin sürelerini kaydettim. Person sınıfı, ActiveJDBC kullanarak veritabanı işlemlerini modelledi. PersonService sınıfında CRUD metodlarını yazdım: kişi kaydetme, kişileri listeleme, silme işlemleri vb. API'yi ise PersonController ile yönettim, burada POST, GET, ve DELETE isteklerini kontrol ettim. Bu sayede, veritabanı işlemlerini basit ve etkili bir şekilde yönettim.