package tr.com.huseyinaydin.spring.aop.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
	@Id
	private int id;
	private String name;
	private double price;
}