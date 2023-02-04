package tr.com.huseyinaydin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@SpringBootApplication
@Controller
public class MyPortalClientApplication {

	@GetMapping("/")
	public String home() {
		return "home";
	}

	/*
	 * 'http://localhost:8080/access' from origin 'http://localhost:9090' has been
	 * blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on
	 * the requested resource.
	 */

	public static void main(String[] args) {
		SpringApplication.run(MyPortalClientApplication.class, args);
	}

}
