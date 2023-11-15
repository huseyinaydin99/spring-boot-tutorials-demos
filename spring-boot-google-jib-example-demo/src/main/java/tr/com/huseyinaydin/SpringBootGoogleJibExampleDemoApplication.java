package tr.com.huseyinaydin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot, Google Jib, Docker, Dockerize Canım!.
 *
 */

@SpringBootApplication
@RestController
public class SpringBootGoogleJibExampleDemoApplication {

	@GetMapping("/message")
	public String message(){
		return "Bu Spring Boot uygulaması Google Jib kullanılarak Docker'laştırılmıştır.";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGoogleJibExampleDemoApplication.class, args);
	}
}