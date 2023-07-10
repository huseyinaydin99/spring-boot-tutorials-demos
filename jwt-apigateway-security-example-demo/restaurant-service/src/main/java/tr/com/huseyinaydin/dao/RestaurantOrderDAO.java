package tr.com.huseyinaydin.dao;

import org.springframework.stereotype.Component;

import tr.com.huseyinaydin.dto.OrderResponseDTO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@Component
public class RestaurantOrderDAO {

    public OrderResponseDTO getOrders(String orderId) {
        return generateRandomOrders().get(orderId);
    }

    private Map<String, OrderResponseDTO> generateRandomOrders() {
        Map<String, OrderResponseDTO> orderMap = new HashMap<>();
        orderMap.put("35fds631", new OrderResponseDTO("35fds63", "Şiş kebap", 1, 199, new Date(), "READY", 15));
        orderMap.put("9u71245h", new OrderResponseDTO("9u71245h", "Acılı Adana kebap", 2, 641, new Date(), "PREPARING", 59));
        orderMap.put("37jbd832", new OrderResponseDTO("37jbd832", "Kıymalı pide", 1, 325, new Date(), "DELIVERED", 0));
        return orderMap;
    }

    public static void main(String[] args) {
        System.out.println("Ekmek arası köfte.! ımm leziz.! (-:".toUpperCase());
    }
}