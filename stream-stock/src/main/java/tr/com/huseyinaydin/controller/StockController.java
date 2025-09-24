package tr.com.huseyinaydin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.entity.Stock;
import tr.com.huseyinaydin.service.StockService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.OutputStream;
import java.util.List;
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
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }

    @GetMapping("/stream")
    public StreamingResponseBody streamStocks(HttpServletResponse response) {
        response.setContentType("text/event-stream");
        return outputStream -> {
            stockService.getAllStocks()
                    .forEach(stock -> {
                        try {
                            String json = new ObjectMapper()
                                    .writeValueAsString(stock);
                            outputStream.write(json.getBytes());
                            outputStream.flush();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex.getMessage());
                        }
                    });
        };
    }

    @GetMapping("/live")
    public StreamingResponseBody streamStockPrices(HttpServletResponse response) {
        response.setContentType("text/event-stream"); // bununla tarayıcı/istemciler bunu bir akış/stream olarak ele alır

        return (OutputStream outputStream) -> {
            try {
                for (int i = 0; i < 20; i++) { // 20 güncellemeyi simüle et döngü ile
                    double price = 100 + new Random().nextDouble() * 10; // rastgele stok fiyatı
                    String update = "Stock Fiyat: " + price;

                    outputStream.write(update.getBytes());
                    outputStream.flush();

                    Thread.sleep(1000); // her 1 saniyede yeni güncelleme uyutma - 1 saniye = 1000 ms
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}