package tr.com.huseyinaydin.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import tr.com.huseyinaydin.dto.Customer;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@Service
public class KafkaMessageListener {

    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "huseyinaydin-demo",groupId = "huso-group_1")
    public void consumeEvents(Customer customer) {
        log.info("consumer consume the events {} ", customer.toString());
    }

//    @KafkaListener(topics = "huseyinaydin-demo1",groupId = "huso-group_2")
//    public void consume2(String message) {
//        log.info("consumer2 consume the message {} ", message);
//    }
//
//    @KafkaListener(topics = "huseyinaydin-demo1",groupId = "huso-group_2")
//    public void consume3(String message) {
//        log.info("consumer3 consume the message {} ", message);
//    }
//
//    @KafkaListener(topics = "huseyinaydin-demo1",groupId = "huso-group_2")
//    public void consume4(String message) {
//        log.info("consumer4 consume the message {} ", message);
//    }
}