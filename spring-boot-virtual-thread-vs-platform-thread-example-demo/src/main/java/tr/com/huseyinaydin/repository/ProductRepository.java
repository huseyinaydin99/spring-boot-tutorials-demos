package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot Virtual Thread vs Platform Thread.
 *
 */

public interface ProductRepository extends JpaRepository<Product,Long> {
}