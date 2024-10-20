package tr.com.huseyinaydin.response;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

public record PaymentResponse(boolean isSuccess, Long transactionId) {

    public PaymentResponse {
        if (transactionId < 0) {
            throw new IllegalArgumentException("Transaction Id'i dana gözünden küçük olamaz.");
        }
    }
}