package tr.com.huseyinaydin.jsondocs.api;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot JSONDocs.
* 
*/

@SpringBootApplication
@EnableJSONDoc
public class SpringJsonDocsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJsonDocsApplication.class, args);
	}
}