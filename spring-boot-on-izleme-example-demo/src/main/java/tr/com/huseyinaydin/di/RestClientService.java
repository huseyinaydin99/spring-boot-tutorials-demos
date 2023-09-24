package tr.com.huseyinaydin.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Component
public class RestClientService {

    private OrderService orderService;

    @Autowired
    @Lazy
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
