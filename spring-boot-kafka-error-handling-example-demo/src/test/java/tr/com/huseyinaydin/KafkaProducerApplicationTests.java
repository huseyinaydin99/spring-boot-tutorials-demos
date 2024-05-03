package tr.com.huseyinaydin;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import tr.com.huseyinaydin.dto.Customer;
import tr.com.huseyinaydin.publisher.KafkaMessagePublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.stream.IntStream;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot.
 * @since 1994
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class KafkaProducerApplicationTests {

    @Container
    static final KafkaContainer kafka =
            new KafkaContainer(DockerImageName.parse("huseyin11/cp-kafka:latest"));

    @DynamicPropertySource
    static void overridePropertiesInternal(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
        registry.add(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class::getName);
        registry.add(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class::getName);
    }

    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;

    @Test
    public void testSendMessageToTopic() {
        IntStream.rangeClosed(1, 10)
                        .forEach(i->
                                kafkaMessagePublisher.sendEvents(new Customer(i, "testUser"+i, "test@gmail.com", "43134612167")));
        await().pollInterval(Duration.ofSeconds(3)).atMost(10, SECONDS).untilAsserted(() -> {

        });
    }
}