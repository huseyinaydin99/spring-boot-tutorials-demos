package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.entity.Product;
import tr.com.huseyinaydin.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product updateStockQuantity(Integer productId, Integer quantity){
        productRepository.updateStock(productId, quantity);
        return productRepository.findById(productId).get();
    }

    public Double calculateProductPrice(int id){
        return productRepository.getTotalPrice(id);
    }
}