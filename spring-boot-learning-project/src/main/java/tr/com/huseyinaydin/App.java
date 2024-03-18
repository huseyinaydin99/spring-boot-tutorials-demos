package tr.com.huseyinaydin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import tr.com.huseyinaydin.properties.CarWasherProperties;

//بسم الله الرحمن الرحيم
/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@SpringBootApplication
@EnableConfigurationProperties(value=CarWasherProperties.class)
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}