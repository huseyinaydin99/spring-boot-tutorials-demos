package tr.com.huseyinaydin.exception;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 */

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(Long id) {

        super(String.format("Course Id %d not found", id));
    }
}