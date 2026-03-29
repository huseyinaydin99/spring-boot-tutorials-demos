package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.entity.Order;
import tr.com.huseyinaydin.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;

    public Order createOrder(Order order) {
        return repository.save(order);
    }

    public List<Order> getAll() {
        return repository.findAll();
    }

    public List<Order> findByProduct(String name) {
        return repository.findByProductName(name);
    }
}