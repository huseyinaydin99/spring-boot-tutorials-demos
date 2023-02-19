package tr.com.huseyinaydin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.entity.Product;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

public interface ProductRepository extends JpaRepository<Product,Long> {
   // Product findBySupplierCode(String supplierCode);
}
