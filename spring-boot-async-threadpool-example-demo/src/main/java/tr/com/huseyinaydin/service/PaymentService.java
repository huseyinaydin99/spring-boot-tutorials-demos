package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Service
@Slf4j
public class PaymentService {

    public void processPayment(Order order) throws InterruptedException {
        log.info("Ödeme işlemi öncesi " + order.getProductId());
        //call actual payment gateway
        Thread.sleep(2000L);
        log.info("Ödeme işlemi sonrası " + order.getProductId());
    }
}