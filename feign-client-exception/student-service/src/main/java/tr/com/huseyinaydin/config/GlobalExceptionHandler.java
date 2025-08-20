package tr.com.huseyinaydin.config;

import feign.FeignException;
import feign.RetryableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

/*
FeignClientConfig içinde tanımladığım CustomErrorDecoder aslında Feign seviyesinde hataları nasıl
decode edeceğimi belirliyor ⚡; yani uzak servisten 400, 404 veya 500 gibi bir hata döndüğünde
bu decoder devreye girip bana özelleştirilmiş bir exception fırlatıyor 🎯. GlobalExceptionHandler
ise tam bu noktada devreye giriyor 🚀; Feign’in decode ettiği veya doğrudan fırlattığı
bu exception’ları yakalayıp 🌐, kullanıcıya anlamlı, yönetilebilir ve doğru HTTP response’ları dönüyor
📚. Kısacası FeignClientConfig → hatayı dönüştüren katman,
GlobalExceptionHandler → bu hatayı yöneten ve dışarıya düzgün şekilde ileten katman;
birlikte çalışarak mikroservisler arası hata yönetimini merkezi, okunabilir ve sürdürülebilir hale getiriyor ✅.
*/

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.BadRequest.class)
    public ResponseEntity<String> handleBadRequest(FeignException.BadRequest ex) {
        return ResponseEntity.badRequest()
                .body(ex.contentUTF8());
    }

    @ExceptionHandler(FeignException.InternalServerError.class)
    public ResponseEntity<String> handleServerError(FeignException.InternalServerError ex) {
        return ResponseEntity.internalServerError()
                .body(ex.contentUTF8());
    }

    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    public ResponseEntity<String> handleServiceUnavailable(FeignException.ServiceUnavailable ex) {
        return ResponseEntity.internalServerError()
                .body(ex.contentUTF8());
    }

    @ExceptionHandler(RetryableException.class)
    public ResponseEntity<String> handleRetryableException(RetryableException ex) {
        return ResponseEntity.internalServerError()
                .body(ex.contentUTF8());
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignException(FeignException ex) {
        int status = ex.status();
        String message = ex.contentUTF8();

        return switch (status) {
            case 400 -> ResponseEntity.badRequest().body(message);
            case 500 -> ResponseEntity.internalServerError().body(message);
            case 503 -> ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(message);
            default -> ResponseEntity.status(status).body("Unexpected error: " + message);
        };

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.internalServerError()
                .body(ex.getMessage());
    }
}