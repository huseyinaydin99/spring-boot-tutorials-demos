package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.entity.Order;
import tr.com.huseyinaydin.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public Order save(@RequestBody Order order) {
        return service.createOrder(order);
    }

    @GetMapping
    public List<Order> getAll() {
        return service.getAll();
    }

    @GetMapping("/search")
    public List<Order> search(@RequestParam String product) {
        return service.findByProduct(product);
    }
}