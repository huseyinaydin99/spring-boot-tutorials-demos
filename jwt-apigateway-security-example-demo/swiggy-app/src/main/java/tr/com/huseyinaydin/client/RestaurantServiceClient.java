package tr.com.huseyinaydin.client;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tr.com.huseyinaydin.dto.OrderResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@Component
public class RestaurantServiceClient {
    @Autowired
    private RestTemplate template;

    public OrderResponseDTO fetchOrderStatus(String orderId) {
        return template.getForObject("http://RESTAURANT-SERVICE/restaurant/orders/status/" + orderId, OrderResponseDTO.class);
    }
}