package tr.com.huseyinaydin.logging.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class SpringBootLoggingApplication {

	Logger log = LoggerFactory.getLogger(SpringBootLoggingApplication.class);

	@GetMapping("/test/{name}")
	public String greeting(@PathVariable String name) {
		log.debug("İstek {}", name);
		if (name.equalsIgnoreCase("test")) {
			throw new RuntimeException("Hata oluştu!");
		}
		String response = "Merhaba sayın " + name + " hoş geldin gardaşım nörüyon?";
		log.debug("Cevap {}", response);
		return response;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLoggingApplication.class, args);
	}
}