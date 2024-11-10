package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.entity.Product;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

public interface ProductService {
    Product findById(Long id);
}