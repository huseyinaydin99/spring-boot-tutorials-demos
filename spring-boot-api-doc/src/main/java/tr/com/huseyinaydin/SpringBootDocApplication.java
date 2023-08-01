package tr.com.huseyinaydin;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//بسم الله الرحمن الرحيم

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 * 
 */

@EnableWebMvc
@SpringBootApplication
@Api(value = "My Pet API documentation")
public class SpringBootDocApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDocApplication.class, args);
	}
}