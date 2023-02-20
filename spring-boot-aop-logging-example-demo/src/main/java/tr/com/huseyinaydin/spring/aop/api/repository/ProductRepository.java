package tr.com.huseyinaydin.spring.aop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.spring.aop.api.model.Product;

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 * 
 */

public interface ProductRepository extends JpaRepository<Product, Integer>{

}