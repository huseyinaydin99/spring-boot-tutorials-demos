package tr.org.huseyinaydin.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static tr.org.huseyinaydin.common.KafkaConfigProperties.SEAT_RESERVED_TOPIC;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic createTransactionTopic() {
        return new NewTopic(SEAT_RESERVED_TOPIC, 3, (short) 1);
    }
}