package tr.com.huseyinaydin.service.impl;

import tr.com.huseyinaydin.entity.Product;
import tr.com.huseyinaydin.repository.ProductRepository;
import tr.com.huseyinaydin.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(Long id) {
        log.info("productId'ye göre ürünün çekilmesi {}", id);
        addDelay(); //2000 milisaniye yani 2 saniye bekletilmesi
        return productRepository.findById(id).orElse(null);
    }

    private void addDelay() {
        try {
            Thread.sleep(2000); //2000 milisaniye yani 2 saniye bekletilmesi
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}