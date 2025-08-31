package tr.com.huseyinaydin.adapter.output;

import tr.com.huseyinaydin.adapter.output.entity.OrderEntity;
import tr.com.huseyinaydin.adapter.output.repository.SpringDataOrderRepository;
import tr.com.huseyinaydin.domain.dto.FoodOrder;
import tr.com.huseyinaydin.domain.port.output.OrderRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Repository
public class JpaOrderRepository implements OrderRepositoryPort {

    @Autowired
    private SpringDataOrderRepository repository;

    @Override
    public void saveOrder(FoodOrder order) {
        System.out.println("--ÇIKIŞ ADAPTÖRÜ ÇIKIŞ PORTU İLE ÇALIŞTIRILDI--");
        repository.save(mapToEntity(order));
    }

    @Override
    public String findById(String orderId) {
        OrderEntity entity = repository.findById(orderId).orElseThrow();
        System.out.println("--ÇIKIŞ ADAPTÖRÜ ÇIKIŞ PORTU İLE ÇALIŞTIRILDI--");
        return mapToDomain(entity).getStatus();
    }

    private OrderEntity mapToEntity(FoodOrder order) {
        OrderEntity entity = new OrderEntity();
        entity.setOrderId(order.getOrderId());
        entity.setCustomerName(order.getCustomerName());
        entity.setRestaurantName(order.getRestaurantName());
        entity.setItem(order.getItem());
        entity.setStatus(order.getStatus());
        return entity;
    }

    private FoodOrder mapToDomain(OrderEntity entity) {
        FoodOrder order = new FoodOrder();
        order.setOrderId(entity.getOrderId());
        order.setCustomerName(entity.getCustomerName());
        order.setRestaurantName(entity.getRestaurantName());
        order.setItem(entity.getItem());
        order.setStatus(entity.getStatus());
        return order;
    }
}