package tr.com.huseyinaydin.adapter.output.repository;

import tr.com.huseyinaydin.adapter.output.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

public interface SpringDataOrderRepository extends JpaRepository<OrderEntity, String> {
}