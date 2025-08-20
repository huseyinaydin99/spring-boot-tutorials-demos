package tr.com.huseyinaydin.client;

import tr.com.huseyinaydin.config.FeignClientConfig;
import tr.com.huseyinaydin.dto.Course;
import tr.com.huseyinaydin.dto.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

/*
🎯 Feign Client Nedir?
Feign Client, Spring Cloud ekosisteminde kullanılan, REST servislerine HTTP çağrılarını arka planda otomatik yapan bir kütüphane.
Yani normalde RestTemplate veya WebClient ile manuel yazacağım HTTP request–response kodlarını sadece bir arayüz tanımlayarak hallettim.
Böylece mikroservisler arası iletişimi daha basit, okunabilir ve yönetilebilir hale getirdim.

⚡ Ne işe yarar?
Servisler arası otomatik proxy oluşturur → Ben kod yazmadan Feign, HTTP isteklerini hazırlar.
Kod tekrarını önler → Tek satırlık metot tanımıyla REST endpoint’e bağlandım.
Bağımlılığı azaltır → Her mikroservis kendi işine odaklanırken, iletişimi Feign üstlenir.

Servisler arası iletişimi çok daha sade hale getirdim.
REST çağrılarını satırlarca kod yerine tek bir arayüzle yönettim.
Kurs servisini, diğer mikroservisler tarafından kolayca tüketilebilir hale getirdim.
Kısacası: İletişimi otomatikleştirdim, kodu sadeleştirdim, sistemi esnek hale getirdim. 🌉
 */

@FeignClient(name = "course-client", url = "${application.services.course.url}", configuration = FeignClientConfig.class)
public interface CourseClient {

    @GetMapping
    List<Course> courses();

    @GetMapping("/{id}")
    Course course(@PathVariable int id, @RequestHeader(name = "X-Request-Source") String sourceSystem);

    @PostMapping("/{id}/ratings")
    String submitRating(@PathVariable int id, @RequestBody Rating rating);
}