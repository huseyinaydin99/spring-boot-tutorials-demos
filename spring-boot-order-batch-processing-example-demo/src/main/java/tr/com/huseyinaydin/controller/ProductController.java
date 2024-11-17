package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.service.ProductService;
import tr.com.huseyinaydin.service.ProductServiceV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    private final ProductServiceV2 productServiceV2;

    public ProductController(ProductService productService,ProductServiceV2 productServiceV2) {
        this.productService = productService;
        this.productServiceV2 =productServiceV2;
    }

    //test amaçlı
    @GetMapping("/ids")
    public ResponseEntity<List<Long>> getIds() {
        return ResponseEntity.ok(productService.getProductIds());
    }

    //kayıtları resetler
    @PostMapping("/reset")
    public ResponseEntity<String> resetProductRecords() {
        String response = productService.resetRecords();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/process")
    public ResponseEntity<String> processProductIds(@RequestBody List<Long> productIds) {
        productService.processProductIds(productIds);
        return ResponseEntity.ok("Ürünler işlendi ve olaylar yayınlandı.");
    }

    @PostMapping("/process/v2")
    public ResponseEntity<String> processProductIdsV2(@RequestBody List<Long> productIds) {
        productServiceV2.executeProductIds(productIds);
        return ResponseEntity.ok("Ürünler işlendi ve olaylar yayınlandı.");
    }
}