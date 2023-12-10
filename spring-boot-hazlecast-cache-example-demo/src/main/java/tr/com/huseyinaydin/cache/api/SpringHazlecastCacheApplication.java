package tr.com.huseyinaydin.cache.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot Hazlecast Cache.
 *
 */

@SpringBootApplication
@EnableCaching
public class SpringHazlecastCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringHazlecastCacheApplication.class, args);
	}
}