package tr.com.huseyinaydin.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Configuration
public class KafkaConfig {

    @Value("${product.discount.update.topic}")
    private String topicName;

    @Bean
    public NewTopic createTopic() {
        return new NewTopic(topicName, 3, (short) 1);
    }
}