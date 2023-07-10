package tr.com.huseyinaydin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String,Object> template;

    public void sendMessageToTopic(String message){
        CompletableFuture<SendResult<String, Object>> future = template.send("huseyin-top-3", message);
        future.whenComplete((result,ex)->{
            if (ex == null) {
                System.out.println("Gönderilen mesaj: " + message +
                        " with offset: " + result.getRecordMetadata().offset());
            } else {
                System.out.println("Gönderilemeyen mesaj: " +
                        message + " oluşan istisna: " + ex.getMessage());
            }
        });
    }
}