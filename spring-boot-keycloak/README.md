## spring-boot-keycloak
Spring Boot REST API'lerini Keycloak ile güvenli hale getirme.

## Görseller;

![Screenshot 1](screenshots/1.png)

![Screenshot 2](screenshots/2.png)

![Screenshot 3](screenshots/3.png)

### Keycloak Nedir? 🔐

Keycloak, kullanıcı kimlik doğrulama ve yetkilendirme işlemlerini merkezi şekilde yönetmek için geliştirilmiş açık kaynaklı bir kimlik ve erişim yönetimi (IAM) platformudur 🌐. Uygulamaların kullanıcı giriş mekanizmalarını sıfırdan yazmak yerine, hazır ve güvenli bir altyapı üzerinden yönetmesini sağlar ⚙️. Tek bir noktadan kullanıcı, rol, oturum ve güvenlik politikalarının yönetilmesine imkân tanır 🧩. Özellikle mikroservis mimarileri, kurumsal sistemler ve birden fazla uygulamanın bulunduğu yapılarda güvenlik süreçlerini standartlaştırır 🚀.

### Keycloak Ne Değildir? ❌

Keycloak bir veritabanı sistemi, backend framework'ü veya doğrudan bir uygulama geliştirme platformu değildir 🛑. İş mantığını çalıştıran, REST API yazan veya uygulamanın ana fonksiyonlarını yöneten bir yapı gibi düşünülmemelidir ⚠️. Ayrıca kullanıcı arayüzü geliştiren bir frontend teknolojisi ya da doğrudan kullanıcı verilerini işleyen bir ERP sistemi de değildir 🖥️. Görevi uygulamanın iş süreçlerini yürütmek değil, güvenli erişim ve kimlik yönetimi katmanını üstlenmektir 🔒.

### Keycloak Ne İşe Yarar? 🧠

Keycloak kullanıcıların sisteme giriş yapmasını, kimliğinin doğrulanmasını ve hangi işlemleri yapabileceğinin belirlenmesini sağlar 🔑. Kullanıcı rolleri ve yetkileri merkezi olarak yönetildiği için her projede ayrı ayrı giriş sistemi geliştirme ihtiyacını azaltır ⚡. Örneğin Spring Boot API, Angular istemcisi ve mobil uygulama aynı kullanıcı sistemi üzerinden yönetilebilir 📡. OAuth2, OpenID Connect ve SAML gibi standartları destekleyerek farklı teknolojiler arasında güvenli iletişim kurulmasına yardımcı olur 🔄.

### Keycloak'un Ana Amacı Nedir? 🎯

Keycloak'un temel amacı uygulamalardaki kimlik doğrulama ve yetkilendirme süreçlerini tek merkezde toplamak ve güvenliği standart hale getirmektir 🏢. Böylece geliştiriciler giriş ekranı, token yönetimi, oturum takibi ve güvenlik altyapısını tekrar tekrar geliştirmek zorunda kalmaz 🧰. Merkezi yapı sayesinde bakım maliyetleri azalırken güvenlik politikalarının yönetimi daha kontrollü hale gelir 📈. Özellikle dağıtık sistemlerde ve mikroservis mimarilerinde kullanıcı erişim yönetimini sadeleştirir 🔥.

### Keycloak'un Yan Görevleri Nelerdir? 🛠️

Keycloak yalnızca kullanıcı girişi sağlamaz; aynı zamanda tek oturum açma (SSO), sosyal medya girişleri ve oturum yönetimi gibi ek görevler de üstlenir 🌍. Kullanıcıların Google, GitHub veya kurumsal dizin sistemleriyle giriş yapmasına destek verebilir 🔗. E-posta doğrulama, şifre sıfırlama, iki faktörlü doğrulama ve kullanıcı yaşam döngüsü yönetimi gibi güvenlik işlemlerini de sağlayabilir 📧. Bu nedenle yalnızca bir giriş sistemi değil, geniş kapsamlı bir kimlik yönetim merkezi olarak görev yapar 🏗️.

### Spring Security ile Keycloak İlişkisi 🤝

Spring Security ve Keycloak birlikte kullanıldığında, güvenlik sorumluluklarını iki katmana ayıran tamamlayıcı bir yapı oluşturur 🔐. Keycloak kimlik doğrulama (authentication) işlemini yani kullanıcının kim olduğunu doğrulama sürecini merkezi olarak yönetir 🌐, Spring Security ise uygulama içinde yetkilendirme (authorization) ve erişim kontrolünü uygular ⚙️. Bu mimaride Keycloak genellikle JWT token üretir ve Spring Security bu token’ı doğrulayarak kullanıcının rol ve izinlerine göre endpoint erişimini kontrol eder 🧩. Sonuç olarak Keycloak dış kimlik sağlayıcı, Spring Security ise uygulama içi güvenlik karar mekanizması gibi çalışır 🚀.

### Çalıştırma

🔐 1. KEYCLOAK’TAN TOKEN ALMA

***Endpoint:***

http://localhost:8180/realms/huseyinaydin99/protocol/openid-connect/token

***🧪 CURL ile login (en net yöntem)***

```bash
curl -X POST "http://localhost:8180/realms/huseyinaydin99/protocol/openid-connect/token" \
-H "Content-Type: application/x-www-form-urlencoded" \
-d "client_id=spring-boot-keycloak" \
-d "username=admin" \
-d "password=admin" \
-d "grant_type=password"
📦 2. GELEN RESPONSE (JWT)
{
"access_token": "eyJhbGciOiJSUzI1NiIs...",
"expires_in": 300,
"token_type": "Bearer"
}
```
***👉 Buradaki access_token = JWT***

***🚀 3. SPRING’E TOKEN GÖNDERME***
***API:***
http://localhost:9090/

***🔥 HEADER ile çağır:***

```bash
curl http://localhost:9090/ \
-H "Authorization: Bearer eyJhbGciOiJSUzI1NiIs..."
```