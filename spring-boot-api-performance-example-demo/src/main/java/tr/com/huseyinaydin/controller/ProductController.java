package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.dto.ProductDetailDTO;
import tr.com.huseyinaydin.facade.ProductASyncFacade;
import tr.com.huseyinaydin.facade.ProductSyncFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductSyncFacade productSyncFacade;

    @Autowired
    private ProductASyncFacade productASyncFacade;

    @GetMapping("/{id}/sync")
    public ResponseEntity<ProductDetailDTO> getProductSync(@PathVariable Long id) {
        log.info("REST API isteği. Senkron bir yapıda ürün detayları: {}", id);
        return ResponseEntity.ok(productSyncFacade.getProductDetails(id));
    }

    @GetMapping("/{id}/async")
    public ResponseEntity<ProductDetailDTO> getProductAsync(@PathVariable Long id) {
        log.info("REST API isteği. ASenkron bir yapıda ürün detayları: {}", id);
        return ResponseEntity.ok(productASyncFacade.getProductDetails(id));
    }
}