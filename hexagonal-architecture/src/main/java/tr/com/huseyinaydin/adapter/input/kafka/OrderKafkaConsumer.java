package tr.com.huseyinaydin.adapter.input.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.domain.dto.FoodOrder;
import tr.com.huseyinaydin.domain.port.input.PlaceOrderUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Component
public class OrderKafkaConsumer {

    @Autowired
    private PlaceOrderUsecase placeOrderUseCase;

    //@KafkaListener(topics = "food-order-topic", groupId = "order-group")
    public void consume(String message) throws JsonProcessingException {
        // Mesajın bir JSON string olduğunu varsayalım ve bu JSON, bir FoodOrder'ı temsil ediyor
        // Normalde Jackson gibi bir kütüphane kullanarak JSON'u FoodOrder nesnesine dönüştürürdük
        // Basitlik açısından, mesajın doğrudan dönüştürülebilir olduğunu varsayalım
        ObjectMapper mapper = new ObjectMapper();
        FoodOrder order = mapper.readValue(message, FoodOrder.class);
        placeOrderUseCase.placeOrder(order);
        System.out.println("Kafka üzerinden verilen sipariş alındı: " + order);
    }
}