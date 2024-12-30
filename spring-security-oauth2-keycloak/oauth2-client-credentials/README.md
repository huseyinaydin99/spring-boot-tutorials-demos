Bu projede, Spring Boot ve Keycloak kullanarak bir microservice mimarisi oluşturma sürecini başarıyla tamamladım. Amacım, güvenli ve ölçeklenebilir bir yapı kurmak, aynı zamanda OAuth2 protokolünü kullanarak kimlik doğrulama ve yetkilendirme işlemlerini doğru bir şekilde yapılandırmaktı. Bu yazıda, projede kullandığım temel teknolojileri, güvenlik yapılandırmalarını ve özellikle application.properties dosyasındaki ayarları ayrıntılı bir şekilde ele alacağım.

Proje Mimarisi ve Kullanılan Teknolojiler
Projede Spring Boot kullanarak iki farklı microservice geliştirdim. Birinci microservice (Microservice 1) ile kullanıcıdan gelen istekleri alıp, ikinci microservice'e (Microservice 2) yönlendirme yaparak bir API Gateway işlevi gördüm. Bu mimaride, Keycloak kullanarak kimlik doğrulama işlemlerini gerçekleştirdim ve OAuth2 ile güvenlik altyapısını sağladım.

Keycloak ile OAuth2 Kimlik Doğrulama
Keycloak, OAuth2 protokolü kullanarak güvenli kimlik doğrulama ve yetkilendirme sağlıyor. Her iki microservice için de kimlik doğrulama işlemini Keycloak üzerinden yönetiyorum. Her iki serviste de Spring Security ve OAuth2 kaynak sunucusu yapılandırması kullanıldı.

SecurityConfig Sınıfı - Güvenlik Yapılandırması
Her iki microservice için de güvenlik ayarlarını yönetmek için Spring Security kullanarak SecurityConfig sınıfını yapılandırdım. Bu yapılandırma sayesinde, her iki serviste de gelen HTTP istekleri güvenlik denetiminden geçiyor.

java
Kodu kopyala
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.cors(Customizer.withDefaults()) // CORS yapılandırması
                .csrf(CsrfConfigurer::disable) // CSRF korumasını devre dışı bırakma
                .authorizeHttpRequests(httpRequests -> httpRequests.anyRequest().authenticated()) // Tüm isteklerin kimlik doğrulama gerektirmesi
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session yönetimi
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults())) // OAuth2 kaynak sunucusu ve JWT yapılandırması
                .build();
    }
}
Bu yapılandırma, servislerimin sadece kimlik doğrulaması yapılmış kullanıcılar tarafından erişilebilir olmasını sağladı. Stateful yerine Stateless session yönetimi kullanarak, her isteğin bağımsız ve güvenli bir şekilde işlenmesini garanti altına aldım. Ayrıca, JWT (JSON Web Token) kullanarak, her microservice'in kimlik doğrulama için token bazlı güvenliği sağlamasını mümkün kıldım.

Controller Yapıları
Microservice 1’de, kullanıcıdan gelen istekleri alıp, bu istekleri Microservice 2'ye yönlendiren bir controller yapısı kurdum. Bu işlem için, Spring’in RestTemplate ve WebClient sınıflarını kullandım. RestTemplate ile gelen isteklere token ekleyip, Microservice 2'ye yönlendirme yaparak, oradaki veriyi aldım. Aynı işlemi WebClient ile de yaptım, bu da asenkron işlem yapabilme avantajı sağladı.

java
Kodu kopyala
@RestController
public class Controller1 {

    private final RestTemplate restTemplate = new RestTemplateBuilder().build(); // Microservice'ler arası istemci
    private final WebClient webClient = WebClient.builder().build(); // WebClient ile asenkron istekler

    @GetMapping("/microservice1/home")
    @ResponseStatus(HttpStatus.OK)
    public String hello() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwt.getTokenValue());

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8084/microservice2/home", HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class);

        return "Selamlar burası Microservice 1! " + response.getBody();
    }

    @GetMapping("microservice1/home/webclient")
    @ResponseStatus(HttpStatus.OK)
    public String helloWebClient(){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String response = webClient.get()
                .uri("http://localhost:8084/microservice2/home")
                .headers(header -> header.setBearerAuth(jwt.getTokenValue()))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return "Merhabalar - burası Microservice 1! " + response;
    }
}
Bu controller, Spring Security ile entegre bir şekilde çalışarak, gelen her isteğe JWT token ekleyerek Microservice 2'ye güvenli bir şekilde istek göndermemi sağladı. Bu, sistemin mikro servis mimarisine uygun, güvenli bir yapı halinde çalışmasını sağladı.

application.properties - Konfigürasyon Ayarları
application.properties dosyası, Spring Security ve OAuth2 ile ilgili kritik ayarları içeriyor. Burada, Keycloak için gerekli olan client ID, client secret, authorization grant tipi ve resource server JWT yapılandırması gibi bilgiler yer alıyor. Bu ayarları doğru bir şekilde yapılandırarak, Spring Boot uygulamamı Keycloak ile entegre hale getirdim.

Microservice 1 ve Microservice 2 için application.properties ayarları şunlar:

Microservice 1 (server.port = 8083):

properties
Kodu kopyala
server.port = 8083

spring.security.oauth2.client.registration.oauth2-demo-thymeleaf-client.client-id = oauth2-demo-credentials
spring.security.oauth2.client.registration.oauth2-demo-thymeleaf-client.client-secret = c772c737-d143-4dc6-a09f-98481fb670c8
spring.security.oauth2.client.registration.oauth2-demo-thymeleaf-client.scope = openid, profile, roles
spring.security.oauth2.client.registration.oauth2-demo-thymeleaf-client.authorization-grant-type = authorization_code
spring.security.oauth2.client.registration.oauth2-demo-thymeleaf-client.redirect-uri = http://localhost:8083/login/oauth2/code/oauth2-demo-credentials
spring.security.oauth2.client.provider.oauth2-demo-thymeleaf-client.issuer-uri = http://localhost:8080/auth/realms/oauth2-demo-realm

spring.security.oauth2.resourceserver.jwt.jwk-set-uri = http://localhost:8080/auth/realms/oauth2-demo-realm/protocol/openid-connect/certs
Microservice 2 (server.port = 8084):

properties
Kodu kopyala
server.port = 8084

spring.security.oauth2.client.registration.oauth2-demo-thymeleaf-client.client-id = oauth2-demo-credentials
spring.security.oauth2.client.registration.oauth2-demo-thymeleaf-client.client-secret = c772c737-d143-4dc6-a09f-98481fb670c8
spring.security.oauth2.client.registration.oauth2-demo-thymeleaf-client.scope = openid, profile, roles
spring.security.oauth2.client.registration.oauth2-demo-thymeleaf-client.authorization-grant-type = authorization_code
spring.security.oauth2.client.registration.oauth2-demo-thymeleaf-client.redirect-uri = http://localhost:8083/login/oauth2/code/oauth2-demo-credentials
spring.security.oauth2.client.provider.oauth2-demo-thymeleaf-client.issuer-uri = http://localhost:8080/auth/realms/oauth2-demo-realm

spring.security.oauth2.resourceserver.jwt.jwk-set-uri = http://localhost:8080/auth/realms/oauth2-demo-realm/protocol/openid-connect/certs
Bu ayarlar, Spring Security'nin OAuth2 ile entegrasyonunu sağlayarak, her iki mikro servisin de güvenli bir şekilde çalışmasını ve Keycloak ile kimlik doğrulaması yapmasını mümkün kıldı. JWT token'ları kullanarak her iki microservice arasında güvenli iletişim sağlandı.

Sonuç
Bu proje ile iki microservice arasında güvenli bir iletişim altyapısı kurmuş oldum. Spring Security, Keycloak ve OAuth2 entegrasyonu sayesinde, her iki servisin kimlik doğrulama ve yetkilendirme işlemleri sorunsuz bir şekilde gerçekleştirildi. Ayrıca, RestTemplate ve WebClient gibi araçlarla servisler arası veri iletişimi sağlandı. Bu mimari, özellikle büyük ölçekli ve güvenli mikro hizmet tabanlı sistemler için oldukça verimli bir çözüm sunuyor.

Bu projeyi tamamlayarak, modern web uygulamalarının güvenliğini sağlamak için gerekli adımları başarılı bir şekilde attım ve mikro servisler arasında güvenli, ölçeklenebilir bir yapı kurmayı başardım.