package tr.com.huseyinaydin.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.entity.Product;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

public interface ProductRepository extends JpaRepository<Product, Integer> {
}