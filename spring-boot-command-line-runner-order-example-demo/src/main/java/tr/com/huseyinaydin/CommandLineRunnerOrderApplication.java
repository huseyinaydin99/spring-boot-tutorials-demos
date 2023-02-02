package tr.com.huseyinaydin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@SpringBootApplication
public class CommandLineRunnerOrderApplication {

	public static void main(String[] args) {		
		System.out.println("The service to start.");
		SpringApplication.run(CommandLineRunnerOrderApplication.class, args);
		System.out.println("The service has started.");
	}

}
