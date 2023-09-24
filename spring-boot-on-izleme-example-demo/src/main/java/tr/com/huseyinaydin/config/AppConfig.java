package tr.com.huseyinaydin.config;

import tr.com.huseyinaydin.service.DemoService;
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

@Configuration
public class AppConfig {

    @Bean
    public DemoService demoService(){
        return new DemoService();
    }
}
