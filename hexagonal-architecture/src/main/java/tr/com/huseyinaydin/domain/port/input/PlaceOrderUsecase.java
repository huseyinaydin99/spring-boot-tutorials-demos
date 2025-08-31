package tr.com.huseyinaydin.domain.port.input;

import tr.com.huseyinaydin.domain.dto.FoodOrder;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

public interface PlaceOrderUsecase {
    void placeOrder(FoodOrder order);
}