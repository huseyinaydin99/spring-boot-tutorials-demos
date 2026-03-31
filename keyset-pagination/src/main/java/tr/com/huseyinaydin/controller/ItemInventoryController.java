package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.dto.CursorPageResponse;
import tr.com.huseyinaydin.entity.Item;
import tr.com.huseyinaydin.service.ProductInventoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot
 *
 */

@RestController
@RequestMapping("/api/products")
public class ItemInventoryController {

    private final ProductInventoryService service;

    public ItemInventoryController(ProductInventoryService service) {
        this.service = service;
    }

    /**
     * GET /api/products?page={page}&size={size}
     * - page: sonuçların hangi sayfadan başlayacağını belirler (0 = ilk sayfa, varsayılan 0)
     * - size: her sayfada kaç kayıt döneceğini belirler (varsayılan 10)
     */
    @GetMapping
    public ResponseEntity<Page<Item>> listProducts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Item> products = service.getProducts(page, size);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/cursor")
    public CursorPageResponse<Item> list(
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.getProducts(cursor, size);
    }
}