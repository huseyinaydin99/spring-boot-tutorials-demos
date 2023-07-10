package tr.com.huseyinaydin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@SpringBootApplication
@EnableDiscoveryClient
public class SwiggyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwiggyAppApplication.class, args);
	}
}