package tr.com.huseyinaydin.product;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 **/

public interface ProductRepository extends MongoRepository<Product, String> {

	List<Product> findAllByNameIgnoreCase(String name);

	List<Product> findAllByNameStartingWith(String name);

	List<Product> findAllByNameEndingWith(String name);

	List<Product> findAllByNameContainingIgnoreCase(String name);

	List<Product> findAllByPriceLessThan(BigDecimal price);

	List<Product> findAllByPriceGreaterThan(BigDecimal price);

	List<Product> findAllByPriceBetween(BigDecimal price1, BigDecimal price2);

	List<Product> findAllByNameContainingIgnoreCaseOrderByPrice(String name);

	List<Product> findAllByNameContainingIgnoreCaseOrderByPrice(String name, Pageable page);
}
