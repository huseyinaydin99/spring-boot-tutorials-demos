package tr.com.huseyinaydin.di;

import org.springframework.stereotype.Repository;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public void saveOrder() {
        System.out.println("OrderRepository::saveOrder() method executed..");
    }
}
