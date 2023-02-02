package tr.com.huseyinaydin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@SpringBootApplication
@RestController
public class TestApplication {

	@GetMapping("/test")
	public String test() {
		return "Application Deployed";
	}

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
}
