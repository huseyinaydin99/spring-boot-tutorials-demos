package tr.com.huseyinaydin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot examples
 * 
 */

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(DemoApplication.class, args);
		Customer a = context.getBean(Customer.class);
		a.show();
	}
}