package tr.com.huseyinaydin.mapper;

import tr.com.huseyinaydin.dto.ProductResponse;
import tr.com.huseyinaydin.entity.Product;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

public class ProductResponseMapper {

    public static ProductResponse map(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .discountPercentage(product.getDiscountPercentage())
                .priceAfterDiscount(product.getPrice() - (product.getPrice() * product.getDiscountPercentage() / 100))
                .build();
    }
}