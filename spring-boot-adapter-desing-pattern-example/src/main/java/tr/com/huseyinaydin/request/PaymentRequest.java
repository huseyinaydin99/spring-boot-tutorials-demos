package tr.com.huseyinaydin.request;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

public record PaymentRequest(Double amount, String currency) {

    public PaymentRequest {
        if (amount < 0) {
            throw new IllegalArgumentException("Miktar dana gözünden küçük olamaz hacım.");
        }
    }
}