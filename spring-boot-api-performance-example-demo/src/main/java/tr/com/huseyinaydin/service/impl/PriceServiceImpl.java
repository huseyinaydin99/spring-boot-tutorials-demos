package tr.com.huseyinaydin.service.impl;

import tr.com.huseyinaydin.entity.Price;
import tr.com.huseyinaydin.repository.PriceRepository;
import tr.com.huseyinaydin.service.PriceService;
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
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getPriceByProductId(Long productId) {
        log.info("productId'ye göre ürün fiyatının çekilmesi {}", productId);
        addDelay(); //2000 milisaniye yani 2 saniye bekletilmesi
        return priceRepository.findByProductId(productId);
    }

    private void addDelay() {
        try {
            Thread.sleep(2000); //2000 milisaniye yani 2 saniye bekletilmesi
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}