package tr.com.huseyinaydin.pm.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@ToString
public class OrderResponse {
	private OrderRequest response;
	private String message;
	private int statusCode;

}
