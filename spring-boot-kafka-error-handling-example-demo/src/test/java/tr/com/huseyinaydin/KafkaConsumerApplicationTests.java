package tr.com.huseyinaydin;

import tr.com.huseyinaydin.consumer.KafkaMessageConsumer;
import tr.com.huseyinaydin.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot.
 * @since 1994
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class KafkaConsumerApplicationTests {

    @Container
    static final KafkaContainer kafka =
            new KafkaContainer(DockerImageName.parse("huseyin11/cp-kafka:latest"));

    @DynamicPropertySource
    static void overridePropertiesInternal(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }

    @Autowired
    private KafkaMessageConsumer messageConsumer;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Test
    public void testSendMessageToTopic() {
        User customer = new User();
        kafkaTemplate.send("huseyin11", customer);
        await().pollInterval(Duration.ofSeconds(3)).atMost(10, TimeUnit.SECONDS).untilAsserted(() -> {
            System.out.println("huseyin11 topiğine gönderilirken çok uzun sürdü/zaman aşımı meydana geldi iptal oldu.");
        });
    }
}