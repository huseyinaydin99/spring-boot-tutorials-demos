package tr.com.huseyinaydin;

import org.springframework.boot.CommandLineRunner;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public class StartupRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Command Line Runner class invoked!!");
	}
}