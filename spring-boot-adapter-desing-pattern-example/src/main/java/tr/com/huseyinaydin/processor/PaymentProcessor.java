package tr.com.huseyinaydin.processor;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

public interface PaymentProcessor {
    void makePayment(double amount);
}