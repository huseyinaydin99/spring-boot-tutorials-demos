package tr.com.huseyinaydin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tr.com.huseyinaydin.config.MyInterface;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

	@Autowired 
	private MyInterface myInterface;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(myInterface.getAppName());
		System.out.println(myInterface.getAppPortNumber());
	}

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}