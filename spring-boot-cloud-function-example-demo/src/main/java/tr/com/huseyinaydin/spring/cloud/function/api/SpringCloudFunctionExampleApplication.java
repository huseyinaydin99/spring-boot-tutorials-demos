package tr.com.huseyinaydin.spring.cloud.function.api;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 * 
 */

@SpringBootApplication
public class SpringCloudFunctionExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudFunctionExampleApplication.class, args);
	}

	@Bean
	public Function<String, String> reverse() {
		return (input) -> new StringBuilder(input).reverse().toString();
	}

	@Bean
	public Supplier<Book> getBook() {
		return () -> new Book(101, "Core Java");
	}

	@Bean
	public Consumer<String> printMessage() {
		return (input) -> System.out.println(input);
	}
}