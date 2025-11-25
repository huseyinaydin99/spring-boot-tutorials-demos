package tr.org.huseyinaydin.exception;

public class PaymentServiceException extends RuntimeException{
    public PaymentServiceException(String message) {
        super(message);
    }
}