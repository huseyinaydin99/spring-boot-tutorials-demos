package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Procedure(name = "updateStockProcedure")
    void updateStock(Integer productId, Integer quantity);

    @Query(value = "SELECT get_total_price(:productId)", nativeQuery = true)
    Double getTotalPrice(int productId);
}