package tr.com.huseyinaydin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.pulsar.annotation.EnablePulsar;

//بسم الله الرحمن الرحيم
/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot, Apache Pulsar.
 *
 */

@SpringBootApplication
@EnablePulsar
public class SpringPulsarDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringPulsarDemoApplication.class, args);
	}
}