package tr.com.huseyinaydin;

import tr.com.huseyinaydin.service.StockClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@SpringBootApplication
public class StockTradingClientApplication implements CommandLineRunner {

	private StockClientService stockClientService;

	public StockTradingClientApplication(StockClientService stockClientService) {
		this.stockClientService = stockClientService;
	}

	public static void main(String[] args) {
		SpringApplication.run(StockTradingClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("gRPC istemci cevabı : " + stockClientService.getStockPrice("AAPL"));
	}
}