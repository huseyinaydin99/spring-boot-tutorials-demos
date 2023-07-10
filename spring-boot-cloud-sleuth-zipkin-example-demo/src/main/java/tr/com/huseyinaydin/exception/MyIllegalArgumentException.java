package tr.com.huseyinaydin.exception;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 */

public class MyIllegalArgumentException extends IllegalArgumentException{

    public MyIllegalArgumentException() {
        super("Parameter value %d did not match expected type");
    }
}