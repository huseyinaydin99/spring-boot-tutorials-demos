package tr.com.huseyinaydin.publisher;

import tr.com.huseyinaydin.dto.Customer;
import tr.com.huseyinaydin.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot.
 * @since 1994
 */

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${app.topic.name}")
    private String topicName;

    public void sendEvents(User user) {
        try {
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicName, user);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Mesaj gönderildi. Gönderilen mesaj = [" + user.toString() +
                            "] offset = [" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Mesaj gönderilemedi. Bilgiler = [" +
                            user.toString() + "] Hata mesajı : " + ex.getMessage());
                }
            });
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sendEvents(Customer customer) {
        try {
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicName, customer);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Mesaj gönderildi. Gönderilen mesaj = [" + customer.toString() +
                            "] offset = [" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Mesaj gönderilemedi. Bilgiler = [" +
                            customer.toString() + "] Hata mesajı : " + ex.getMessage());
                }
            });
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}