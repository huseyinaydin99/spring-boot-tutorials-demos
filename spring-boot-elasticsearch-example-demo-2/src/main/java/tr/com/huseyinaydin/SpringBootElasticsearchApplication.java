package tr.com.huseyinaydin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "tr.com.huseyinaydin.repository")
public class SpringBootElasticsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootElasticsearchApplication.class, args);
	}
}