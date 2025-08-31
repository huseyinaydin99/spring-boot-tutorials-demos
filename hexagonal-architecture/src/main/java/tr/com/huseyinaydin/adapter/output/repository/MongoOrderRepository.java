package tr.com.huseyinaydin.adapter.output.repository;

import tr.com.huseyinaydin.domain.dto.FoodOrder;
import tr.com.huseyinaydin.domain.port.output.OrderRepositoryPort;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

public class MongoOrderRepository implements OrderRepositoryPort {

    // mongo repoyu enjekte et keyfii olarak baba

    @Override
    public void saveOrder(FoodOrder order) {

    }

    @Override
    public String findById(String orderId) {
        return "";
    }
}