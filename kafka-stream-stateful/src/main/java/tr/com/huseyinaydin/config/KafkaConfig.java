package tr.com.huseyinaydin.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic createTransactionTopic() {
        return new NewTopic("transactions",
                3, (short) 1);
    }

    @Bean
    public NewTopic createFraudAlertTopic() {
        return new NewTopic("fraud-alerts",
                3, (short) 1);
    }
}