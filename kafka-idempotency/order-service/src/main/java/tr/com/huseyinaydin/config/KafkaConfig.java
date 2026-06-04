package tr.com.huseyinaydin.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic createOrderTopic() {
        return new NewTopic("ORDER_TOPIC", 3, (short) 1);
    }
}