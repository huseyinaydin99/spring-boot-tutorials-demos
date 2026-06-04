package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.OrderDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String placeOrder(OrderDto orderDto) {
        // Kafka'ya sipariş yayınlama etkinliği
        orderDto.setRequestId(java.util.UUID.randomUUID().toString());
        kafkaTemplate
                .send("ORDER_TOPIC",orderDto.getOrderId(), orderDto);

        return "Sipariş başarıyla verildi!\n";
    }
}