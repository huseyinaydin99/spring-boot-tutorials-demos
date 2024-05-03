package tr.com.huseyinaydin.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.dto.Customer;
import tr.com.huseyinaydin.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot.
 * @since 1994
 */

@Service
@Slf4j
public class KafkaMessageConsumer {

    @RetryableTopic(attempts = "4")// 4 hata olduğu zaman devreye gir anlamında.
    @KafkaListener(topics = "${app.topic.name}", groupId = "huseyinbaba-group")
    public void consumeEvents(User user, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) {
        try {
            log.info("Tüketilen nesnenin JSON değerleri: {} ilgili topikten geldi: {} offset {}", new ObjectMapper().writeValueAsString(user), topic, offset);
            //validate restricted IP before process the records
            List<String> restrictedIpList = Stream.of("32.241.244.236", "15.55.49.164", "81.1.95.253", "126.130.43.183").collect(Collectors.toList());
            if (restrictedIpList.contains(user.getIpAddress())) {
                throw new RuntimeException("Hata: Geçersiz IP adresi geldi. !");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @RetryableTopic(attempts = "4")// 4 hata olduğu zaman devreye gir anlamında.
    @KafkaListener(topics = "${app.topic.name}", groupId = "huseyinbaba-group")
    public void consumeEvents(Customer customer, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) {
        try {
            log.info("Tüketilen nesnenin JSON değerleri: {} ilgili topikten geldi: {} offset {}", new ObjectMapper().writeValueAsString(customer), topic, offset);
            //validate restricted IP before process the records
            List<String> restrictedNameList = Stream.of("Ahmet","Mehmet","Selami","Cumali").collect(Collectors.toList());
            if (restrictedNameList.contains(customer.getName())) {
                throw new RuntimeException("Hata: Geçersiz email geldi. !");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @DltHandler
    public void listenDLT(User user, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("DLT alındı : {} , ilgili topik {} , offset {}", user.getFirstName(), topic, offset);
    }

    @DltHandler
    public void listenDLT(Customer customer, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("DLT alındı : {} , ilgili topik {} , offset {}", customer.getName(), topic, offset);
    }
}