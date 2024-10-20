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
public class StripeAdapter implements PaymentProcessor {

    public void makePayment(double amount) {
        // Stripe-specific logic to process payment
        System.out.println("Stripe ile ödeme yapıldı. İşte miktar: " + amount);
    }
}