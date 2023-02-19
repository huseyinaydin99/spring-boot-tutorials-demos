package tr.com.huseyinaydin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tr.com.huseyinaydin.category.Category;
import tr.com.huseyinaydin.category.CategoryRepository;
import tr.com.huseyinaydin.product.Product;
import tr.com.huseyinaydin.product.ProductRepository;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 **/

@SpringBootApplication
public class MongoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoDemoApplication.class, args);
	}

	// @Bean
	public CommandLineRunner commandLineRunner(ProductRepository repository, CategoryRepository categoryRepository) {
		return args -> {
			var category = Category.builder().name("cat 1").description("cat 1").build();

			var category2 = Category.builder().name("cat 2").description("cat 2").build();
			categoryRepository.insert(category);
			categoryRepository.insert(category2);
			var product = Product.builder().name("iPhone").description("Smart phone").build();
			// repository.insert(product);
		};
	}
}