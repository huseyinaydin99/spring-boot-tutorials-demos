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

import java.util.concurrent.CompletableFuture;

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
public class ProductASyncFacade {

    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private PriceService priceService;

    public CompletableFuture<Product> getProductById(long productId) {
        return CompletableFuture.supplyAsync(() -> productService.findById(productId));
    }

    public CompletableFuture<Price> getPriceByProductById(long productId) {
        return CompletableFuture.supplyAsync(() -> priceService.getPriceByProductId(productId));
    }

    public CompletableFuture<Inventory> getInventoryByProductId(long productId) {
        return CompletableFuture.supplyAsync(() -> inventoryService.getInventoryByProductId(productId));
    }

    public ProductDetailDTO getProductDetails(long productId) {
        //tüm asenkronları hazırla
        CompletableFuture<Product> productFuture = getProductById(productId);
        CompletableFuture<Price> priceFuture = getPriceByProductById(productId);
        CompletableFuture<Inventory> inventoryFuture = getInventoryByProductId(productId);

        //tüm asenkronları aynı anda devreye sok ve verileri çek. tüm feature'ler farklı thread'da paralel olarak aynı anda devreye girer.
        CompletableFuture.allOf(priceFuture, productFuture, inventoryFuture);

        //sonuçları al
        Product product = productFuture.join();
        Price price = priceFuture.join();
        Inventory inventory = inventoryFuture.join();

        //ürün detayları DTO nesnesini inşa et ve dönder
        return new ProductDetailDTO(productId, product.getCategory().getName(),
                product.getName(), product.getDescription(),
                inventory.getAvailableQuantity(), price.getPrice(),
                inventory.getStatus());
    }
}