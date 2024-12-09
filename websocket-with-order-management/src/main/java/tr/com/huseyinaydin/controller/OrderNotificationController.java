package tr.com.huseyinaydin.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Controller
public class OrderNotificationController {

    @MessageMapping("/order/status") // JavaScript kısmının hedefi ile eşleşen nokta.
    @SendTo("/topic/order") // Tüm abonelere yayınla.
    public OrderUpdate sendOrderUpdate(OrderUpdate orderUpdate) {
        // Sipariş durumu güncelleme işlemi yapılabilir.
        return orderUpdate;
    }
}