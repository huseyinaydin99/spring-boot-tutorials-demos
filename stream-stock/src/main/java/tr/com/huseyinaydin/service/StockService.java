package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.entity.Stock;
import tr.com.huseyinaydin.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Service
@Slf4j
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getAllStocks() {
        List<Stock> stockList = stockRepository.findAll();
        log.info("Veritabanından {} adet stok okundu.", stockList.size());
        return stockList;
    }

    public Flux<Stock> streamStocks(){
        return Flux.fromIterable(stockRepository.findAll())
                .delayElements(Duration.ofSeconds(1));
    }
}