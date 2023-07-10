package tr.com.huseyinaydin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@SpringBootApplication
@EnableScheduling
public class SpringBoot3KafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot3KafkaApplication.class, args);
	}
}