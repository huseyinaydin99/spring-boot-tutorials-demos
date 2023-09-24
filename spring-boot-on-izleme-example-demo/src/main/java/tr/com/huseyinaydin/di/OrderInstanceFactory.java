package tr.com.huseyinaydin.di;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

public class OrderInstanceFactory {

    public static OrderRepository getInstance() {
        return new OrderRepositoryImpl();
    }
}
