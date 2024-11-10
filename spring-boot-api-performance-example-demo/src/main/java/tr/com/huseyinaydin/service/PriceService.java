package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.entity.Price;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

public interface PriceService {
    Price getPriceByProductId(Long productId);
}