package tr.com.huseyinaydin.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@Slf4j
@Component
public class ConsumerComponent {

    @KafkaListener(topics = "${app.spring.topicName}")
    public void listen(String event) {
        log.info("Received Message: " + event);
    }
}