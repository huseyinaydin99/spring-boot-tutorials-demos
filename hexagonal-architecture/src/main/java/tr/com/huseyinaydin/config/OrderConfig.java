package tr.com.huseyinaydin.config;

import tr.com.huseyinaydin.domain.port.output.OrderRepositoryPort;
import tr.com.huseyinaydin.domain.service.OrderService;
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
public class OrderConfig {

    @Bean
    public OrderService orderService(OrderRepositoryPort repository) {
        return new OrderService(repository);
    }
}