package tr.com.huseyinaydin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.huseyinaydin.dao.RestaurantOrderDAO;
import tr.com.huseyinaydin.dto.OrderResponseDTO;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@Service
public class RestaurantService {
    @Autowired
    private RestaurantOrderDAO orderDAO;

    public String greeting() {
        return "Ojj geldiniz to Swiggy Restaurant service'sine :D";
    }

    public OrderResponseDTO getOrder(String orderId) {
        return orderDAO.getOrders(orderId);
    }
}