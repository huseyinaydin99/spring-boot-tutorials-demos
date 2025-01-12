package tr.com.huseyinaydin.os.api.repository;

import tr.com.huseyinaydin.os.api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot, Microservices.
 *
 */

public interface OrderRepository extends JpaRepository<Order, Integer> {

}