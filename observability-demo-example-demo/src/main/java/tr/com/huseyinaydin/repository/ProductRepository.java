package tr.com.huseyinaydin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tr.com.huseyinaydin.entity.Product;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByProductType(String productType);
    //position based param
    @Query("from Product p where p.productType= ?1")
    List<Product> findByProduct(String productType);
    //named parameters
    //@Query("from product p where p.productType= :productType")
    //List<Product> findByProduct2(@Param("productType") String productType);
    List<Product> findByPriceAndProductType(double price, String productType);
    List<Product> findByPriceIn(List<Double> price);
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    List<Product> findByPriceLessThan(double priceRange);
    List<Product> findByPriceGreaterThan(double priceRange);
    List<Product> findByNameIgnoreCaseContaining(String name);
}