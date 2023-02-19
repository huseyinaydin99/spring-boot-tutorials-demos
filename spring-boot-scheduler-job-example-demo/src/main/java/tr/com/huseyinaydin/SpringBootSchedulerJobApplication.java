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

@EnableScheduling
@SpringBootApplication
public class SpringBootSchedulerJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSchedulerJobApplication.class, args);
	}
}