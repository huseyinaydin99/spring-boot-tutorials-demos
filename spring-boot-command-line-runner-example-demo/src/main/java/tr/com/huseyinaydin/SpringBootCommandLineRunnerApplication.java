package tr.com.huseyinaydin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@SpringBootApplication
public class SpringBootCommandLineRunnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCommandLineRunnerApplication.class, args);
	}
	
	@Bean
	public StartupRunner StartupRunner() {
		return new StartupRunner();
	}
}