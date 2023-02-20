package tr.com.huseyinaydin.spring.cloud.function.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 * 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	
	private int id;
	private String name;
}