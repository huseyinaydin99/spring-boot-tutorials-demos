package tr.com.huseyinaydin.domain.port.output;

import tr.com.huseyinaydin.domain.dto.FoodOrder;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

public interface OrderRepositoryPort {
    void saveOrder(FoodOrder order);
    String findById(String orderId);
}