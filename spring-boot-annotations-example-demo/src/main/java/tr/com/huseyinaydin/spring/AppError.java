package tr.com.huseyinaydin.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Data
@AllArgsConstructor
public class AppError {
    private String errorCode;
    private String message;
    private HttpStatus httpStatus;
}
