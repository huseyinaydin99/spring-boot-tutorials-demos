package tr.com.huseyinaydin.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@Configuration
public class SwiggyAppConfig {

    @Bean
    @LoadBalanced
    public RestTemplate template(){
        return new RestTemplate();
    }
}
