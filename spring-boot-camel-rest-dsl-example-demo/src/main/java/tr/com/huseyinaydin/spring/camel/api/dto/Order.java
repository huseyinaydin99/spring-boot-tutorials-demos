package tr.com.huseyinaydin.spring.camel.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@ToString
public class Order {
	
	private int id;
	private String name;
	private double price;
}