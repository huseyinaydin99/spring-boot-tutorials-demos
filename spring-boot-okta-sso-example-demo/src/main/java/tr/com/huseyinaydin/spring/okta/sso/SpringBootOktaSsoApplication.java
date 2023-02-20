package tr.com.huseyinaydin.spring.okta.sso;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 * 
 */

@SpringBootApplication
@RestController
@EnableOAuth2Sso
public class SpringBootOktaSsoApplication {
	
	@GetMapping("/")
	public String welcome2User(Principal principal) {
		return "Selam! "+principal.getName()+" sensei welcome!";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOktaSsoApplication.class, args);
	}
}