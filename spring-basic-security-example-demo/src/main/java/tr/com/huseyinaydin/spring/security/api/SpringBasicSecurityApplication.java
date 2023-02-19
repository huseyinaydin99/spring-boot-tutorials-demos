package tr.com.huseyinaydin.spring.security.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@SpringBootApplication
@EnableWebSecurity
public class SpringBasicSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBasicSecurityApplication.class, args);
	}
}