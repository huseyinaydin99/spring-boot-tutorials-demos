# spring-data-jpa-query-hints

Spring Data JPA'da @QueryHints, bir sorguya özel performans ve davranış ayarları uygulamak için kullanılır. Örneğin, sorgunun yalnızca okuma amacıyla çalışmasını sağlamak için org.hibernate.readOnly kullanılabilir, bu da performansı artırabilir. org.hibernate.fetchSize ile veritabanından kaç satırda bir veri çekileceği belirlenebilir, bu da büyük veri setlerinde bellek yönetimine yardımcı olur. Ayrıca, sorgunun önbelleğe alınmasını sağlamak için org.hibernate.cacheable gibi ayarlar kullanılabilir, böylece tekrar edilen sorgular daha hızlı çalışır.

EmployeeRepository adında bir arayüz oluşturdum ve JpaRepository'yi genişlettim, böylece Employee nesneleri için CRUD işlemleri yapabileceğim bir yapı sağladım.

findEmployeesWithSalaryGreaterThan metodunu yazdım. Bu metodun, maaşı belirtilen değerden yüksek olan çalışanları veritabanından sorgulayan bir JPQL sorgusu çalıştırmasını sağladım.

Sorgunun performansını iyileştirmek amacıyla @QueryHints kullanarak, sorgunun sadece okuma amaçlı (org.hibernate.readOnly), belirli bir boyutta (org.hibernate.fetchSize) ve önbellekleme yaparak (org.hibernate.cacheable) çalışmasını sağladım.

jakarta.persistence.cache.retrieveMode ve jakarta.persistence.cache.storeMode parametreleriyle verilerin önbellekten alınmasını ve saklanmasını aktive ettim.

Son olarak, sorgunun zaman aşımı süresini 2000 milisaniye (2 saniye) olarak ayarladım. Böylece sorgu uzun süre çalışmaya devam ederse, belirttiğim süre sonunda zaman aşımına uğrayacak.