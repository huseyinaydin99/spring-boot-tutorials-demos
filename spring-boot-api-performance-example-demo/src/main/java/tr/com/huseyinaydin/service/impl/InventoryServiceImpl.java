package tr.com.huseyinaydin.service.impl;

import tr.com.huseyinaydin.entity.Inventory;
import tr.com.huseyinaydin.repository.InventoryRepository;
import tr.com.huseyinaydin.service.InventoryService;
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
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory getInventoryByProductId(Long productId) {
        log.info("productId'ye göre envanterin çekilmesi {}", productId);
        addDelay(); //2000 milisaniye yani 2 saniye bekletilmesi
        return inventoryRepository.findByProductId(productId);
    }

    private void addDelay() {
        try {
            //2000 milisaniye yani 2 saniye bekletilmesi
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}