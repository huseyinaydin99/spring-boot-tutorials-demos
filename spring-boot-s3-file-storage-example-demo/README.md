# s3-file-storage-example

```
<dependency>
    <groupId>com.amazonaws</groupId>
    <artifactId>aws-java-sdk</artifactId>
    <version>1.11.486</version>
</dependency>
```

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-aws</artifactId>
</dependency>
```

AWS S3 üzerinde dosya yükleme ve indirme işlemleri için gerekli yapılandırmayı yaptım. @Configuration anotasyonunu kullanarak StorageConfig adlı bir konfigürasyon sınıfı oluşturdum. Bu sınıf, AWS S3 servisine erişim sağlayan AmazonS3 nesnesini oluşturacak.

İlk olarak, AWS kimlik bilgilerini (access-key ve secret-key) ve kullanılacak bölgeyi @Value anotasyonuyla application.properties veya application.yml dosyasından aldım.**

Ardından, s3Client adında bir Bean tanımladım. Bu Bean, AWS kimlik bilgilerini kullanarak AmazonS3 istemcisini yapılandırmakta. AWSCredentials sınıfını kullanarak erişim anahtarı ve gizli anahtar bilgilerini içeren bir kimlik bilgisi nesnesi oluşturdum.

AmazonS3ClientBuilder kullanarak bu kimlik bilgileri ve bölge bilgisiyle yapılandırılmış AmazonS3 istemcisini oluşturup geri döndürdüm. Bu yapılandırma ile proje içinde S3 işlemlerini gerçekleştirecek AmazonS3 nesnesine erişmiş oldum.

AWS S3 ile dosya yükleme, indirme ve silme işlemlerini yöneten StorageService adında bir servis oluşturdum. @Service anotasyonu ile bu sınıfı bir servis bileşeni olarak tanımladım ve @Slf4j anotasyonu ile hata ve işlem mesajları için loglama yeteneği ekledim.

bucketName adında bir değişken tanımladım ve @Value anotasyonu ile bu değeri application.properties dosyasından aldım. Bu, dosyaları yükleyeceğim S3 bucket’ının adını içerecek.

AmazonS3 nesnesini @Autowired ile enjekte ederek AWS S3 istemcisi üzerinden işlemleri gerçekleştirebilir hale geldim.

### uploadFile Yöntemi
Bu metotta, S3’e dosya yükleme işlemini yaptım. İlk olarak, convertMultiPartFileToFile metodunu kullanarak MultipartFile türündeki dosyayı File nesnesine dönüştürdüm.
Yüklenen dosyanın adı olarak System.currentTimeMillis() ile o anki zaman bilgisini dosyanın orijinal ismine ekledim. Bu sayede, aynı isimde birden fazla dosya yüklenmesinin önüne geçtim.
Sonrasında putObject metoduyla bu dosyayı belirttiğim bucket’a yükledim. Yükleme sonrasında geçici olarak oluşturduğum File nesnesini delete() ile sildim.
Bu metodun sonucunda, dosyanın başarıyla yüklendiğine dair bir mesaj döndüm.

### downloadFile Yöntemi
Bu metotta, S3'ten dosya indirme işlemini gerçekleştirdim. fileName parametresi ile S3 üzerinde bulunan dosyayı getObject ile aldım.
S3ObjectInputStream kullanarak dosyanın içeriğini byte dizisine dönüştürdüm ve geri döndürdüm. Bu byte dizisi, indirilen dosyanın içeriğini temsil eder.
Herhangi bir IOException hatasını yakalayarak loglama yaptım ve hata durumunda null döndüm.

### deleteFile Yöntemi
Bu metotta, S3 üzerindeki dosyayı silme işlemi yaptım. deleteObject metoduna bucket adı ve silinecek dosya ismini vererek dosyayı sildim ve başarılı bir silme mesajı döndüm.
convertMultiPartFileToFile Yardımcı Yöntemi
Bu yardımcı metod, MultipartFile nesnesini File nesnesine dönüştürüyor. FileOutputStream ile MultipartFile içeriğini File nesnesine yazdım.
Herhangi bir IOException oluşması durumunda, bunu log.error ile kaydedip hata mesajı bastım.

