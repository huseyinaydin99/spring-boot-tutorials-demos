package tr.com.huseyinaydin.spring.rest.api.dto;

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
public class ErrorMessage {
	
	private int errorCode;
	private String errorMessage;

}
