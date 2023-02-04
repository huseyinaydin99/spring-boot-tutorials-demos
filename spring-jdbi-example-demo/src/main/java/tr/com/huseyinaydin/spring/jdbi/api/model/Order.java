package tr.com.huseyinaydin.spring.jdbi.api.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	private int id;
	private String order_name;
	private int price;
	private int quantity;
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING, timezone = "Europe/Turkey")
	private Date purchaseDate;
}