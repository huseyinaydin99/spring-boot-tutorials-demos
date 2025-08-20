package tr.com.huseyinaydin.exception;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

public class CourseServiceException extends RuntimeException {
    public CourseServiceException(String message) {
        super(message);
    }
}