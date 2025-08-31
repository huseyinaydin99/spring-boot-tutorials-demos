package tr.com.huseyinaydin.adapter.input.rest;

import tr.com.huseyinaydin.domain.dto.FoodOrder;
import tr.com.huseyinaydin.domain.port.input.PlaceOrderUsecase;
import tr.com.huseyinaydin.domain.port.input.TrackOrderUsecase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final PlaceOrderUsecase placeOrderUsecase;
    private final TrackOrderUsecase trackOrderUsecase;

    public OrderController(PlaceOrderUsecase placeOrderUsecase, TrackOrderUsecase trackOrderUsecase) {
        this.placeOrderUsecase = placeOrderUsecase;
        this.trackOrderUsecase = trackOrderUsecase;
    }

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody FoodOrder order) {
        placeOrderUsecase.placeOrder(order);
        System.out.println("--GİRİŞ ADAPTÖRÜ ÇALIŞTIRILDI--");
        return ResponseEntity.ok("Sipariş verildi");
    }

    @GetMapping("/track/{orderId}")
    public ResponseEntity<String> trackOrder(@PathVariable String orderId) {
        System.out.println("--GİRİŞ ADAPTÖRÜ ÇALIŞTIRILDI--");
        return ResponseEntity.ok("Durum: " + trackOrderUsecase.trackOrder(orderId));
    }
}