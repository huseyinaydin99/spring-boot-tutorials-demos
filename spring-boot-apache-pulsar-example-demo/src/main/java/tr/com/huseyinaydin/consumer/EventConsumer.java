package tr.com.huseyinaydin.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.SubscriptionType;
import org.apache.pulsar.common.schema.SchemaType;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.stereotype.Service;

//بسم الله الرحمن الرحيم
/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot, Apache Pulsar.
 *
 */

@Service
@Slf4j
public class EventConsumer {

    @PulsarListener(
            topics = "${spring.pulsar.producer.topic-name1}",
            subscriptionName = "sub1",
            subscriptionType = SubscriptionType.Shared
    )
    public void consumeTextEvent(String msg) {
        log.info("EventConsumer:: consumeTextEvent tüketim metodu {}", msg);
    }


    @PulsarListener(
            topics = "${spring.pulsar.producer.topic-name2}",
            subscriptionName = "sub1",
            schemaType = SchemaType.JSON,
            subscriptionType = SubscriptionType.Shared
    )
    public void consumeRawEvent(CustomerDto customer) throws JsonProcessingException {
        log.info("EventConsumer:: consumeTextEvent tüketim metodu {}", new ObjectMapper().writeValueAsString(customer));
    }
}