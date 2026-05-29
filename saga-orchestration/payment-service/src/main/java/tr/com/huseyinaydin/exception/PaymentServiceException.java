package tr.com.huseyinaydin.exception;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot
 *
 */

public class PaymentServiceException extends RuntimeException{
    public PaymentServiceException(String message) {
        super(message);
    }
}