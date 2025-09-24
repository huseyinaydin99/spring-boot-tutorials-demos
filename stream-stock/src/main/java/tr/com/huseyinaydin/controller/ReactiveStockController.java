package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.entity.Stock;
import tr.com.huseyinaydin.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@RestController
@RequestMapping("/reactive/stocks")
public class ReactiveStockController {

    @Autowired
    private StockService stockService;

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Stock> streamStocks() {
        return stockService.streamStocks();
    }

    @GetMapping(value = "/live", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamStockPrice() {
        return Flux.interval(Duration.ofSeconds(1))
                .take(20)
                .map(i -> {
                    double price = 100 + new Random().nextDouble() * 10;
                    return "Stok fiyat: " + price;
                });
    }
}