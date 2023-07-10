package tr.com.huseyinaydin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 **/

@SpringBootApplication
@EnableCaching
public class SpringBootDataRedisCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataRedisCacheApplication.class, args);
	}
}