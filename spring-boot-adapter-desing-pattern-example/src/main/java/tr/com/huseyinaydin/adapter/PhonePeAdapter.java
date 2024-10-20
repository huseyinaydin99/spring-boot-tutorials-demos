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
public class PhonePeAdapter implements PaymentProcessor {

    @Override
    public void makePayment(double amount) {
        //call actual phonePe api
        System.out.println("PhonePe ile ödeme yapıldı. İşte miktar: " + amount);
    }
}