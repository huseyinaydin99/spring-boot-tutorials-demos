package tr.com.huseyinaydin.exception;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 */

public class NoDataFoundException extends RuntimeException {

    public NoDataFoundException() {
        super("No data found");
    }
}