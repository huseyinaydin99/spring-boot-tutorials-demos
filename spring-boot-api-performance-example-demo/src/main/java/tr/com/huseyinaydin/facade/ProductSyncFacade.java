package tr.com.huseyinaydin.facade;

import tr.com.huseyinaydin.dto.ProductDetailDTO;
import tr.com.huseyinaydin.entity.Inventory;
import tr.com.huseyinaydin.entity.Price;
import tr.com.huseyinaydin.entity.Product;
import tr.com.huseyinaydin.service.InventoryService;
import tr.com.huseyinaydin.service.PriceService;
import tr.com.huseyinaydin.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Component
@Slf4j
public class ProductSyncFacade {

    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private PriceService priceService;

    public ProductDetailDTO getProductDetails(long productId) {
        log.info("Burası senkron bir yapı yani sıra sıra yapılan işlemler. Tek thread üzerinden yürüyor.{}",productId);

        //ürünü Id'ye göre çek
        Product product = productService.findById(productId);

        //fiyatı Id'ye göre çek
        Price price = priceService.getPriceByProductId(productId);

        //envanteri Id'ye göre çek
        Inventory inventory = inventoryService.getInventoryByProductId(productId);

        //sonuçları ürün detay DTO nesnesine geç ve return et.
        return new ProductDetailDTO(productId, product.getCategory().getName(),
                product.getName(), product.getDescription(),
                inventory.getAvailableQuantity(), price.getPrice(),
                inventory.getStatus());
    }
}