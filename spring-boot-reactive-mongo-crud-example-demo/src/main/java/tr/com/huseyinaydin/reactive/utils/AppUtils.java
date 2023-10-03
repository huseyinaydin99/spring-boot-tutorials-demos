package tr.com.huseyinaydin.reactive.utils;

import tr.com.huseyinaydin.reactive.dto.ProductDto;
import tr.com.huseyinaydin.reactive.entity.Product;
import org.springframework.beans.BeanUtils;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

public class AppUtils {
    public static ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}