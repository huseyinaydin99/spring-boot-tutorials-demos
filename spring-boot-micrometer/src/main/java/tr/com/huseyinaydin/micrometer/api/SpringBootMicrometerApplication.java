package tr.com.huseyinaydin.micrometer.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@SpringBootApplication
@RestController
public class SpringBootMicrometerApplication {

	@GetMapping("/message")
	public String getMessage() {
		return "Gızım çünkü çalişiyeah. Biz burda kürtaj yapiyeah...!! (:";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicrometerApplication.class, args);
	}
}