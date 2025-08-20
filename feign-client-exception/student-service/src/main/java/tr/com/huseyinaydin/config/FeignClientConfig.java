package tr.com.huseyinaydin.config;

import tr.com.huseyinaydin.handler.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

/*
Bu konfigurasyonda Feign Client için özel bir ErrorDecoder tanımladım 🚀;
böylece servisler arası iletişimde oluşan hataları (örneğin 400, 404, 500 gibi HTTP durum kodlarını)
doğrudan raw exception olarak almak yerine 🌐,
kontrolüm altında anlamlı ve özelleştirilmiş exception’lara dönüştürdüm ⚡,
bu sayede hem hata yönetimini daha okunabilir ettim 📚
hem de sistemin hata toleransını artırarak mikroservisler arasında
daha profesyonel ve sürdürülebilir bir iletişim sağladım 🎯.
 */

@Configuration
public class FeignClientConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}