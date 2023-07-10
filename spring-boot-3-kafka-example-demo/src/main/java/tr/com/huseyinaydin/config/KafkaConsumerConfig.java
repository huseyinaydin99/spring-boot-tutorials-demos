package tr.com.huseyinaydin.config;

import lombok.RequiredArgsConstructor;
import tr.com.huseyinaydin.dto.FleetStatus;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@EnableKafka
@RequiredArgsConstructor
@Configuration
public class KafkaConsumerConfig {

    private final AppConfig applicationConfig;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, applicationConfig.getBootstrapServers());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, applicationConfig.getConsumerGroupId());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }


    @Bean(name="json")
    public ConsumerFactory<String, FleetStatus> consumerFactoryJson() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, applicationConfig.getBootstrapServers());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, applicationConfig.getConsumerGroupId());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props,
                new StringDeserializer(),
                new JsonDeserializer<>(FleetStatus.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


    @Bean(name="json")
    public ConcurrentKafkaListenerContainerFactory<String, FleetStatus> kafkaListenerContainerFactoryJson() {
        ConcurrentKafkaListenerContainerFactory<String, FleetStatus> factory =new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryJson());
        return factory;
    }
}