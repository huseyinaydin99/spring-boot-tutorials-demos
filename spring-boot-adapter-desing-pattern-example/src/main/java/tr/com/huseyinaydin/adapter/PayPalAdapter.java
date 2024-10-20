package tr.com.huseyinaydin.adapter;

import tr.com.huseyinaydin.processor.PaymentProcessor;
import org.springframework.stereotype.Component;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Component
public class PayPalAdapter implements PaymentProcessor {

    public void makePayment(double amount) {
        // PayPal-specific logic to process payment
        //actual api
        System.out.println("PayPal ile ödeme yapıldı. İşte miktar: " + amount);
    }
}