package tr.com.huseyinaydin.spring.rest.advice;

import lombok.Data;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Data
public class ServiceException extends Exception{

	private int statusCode;

	public ServiceException(String message,int statusCode) {
		super(message);
		this.statusCode=statusCode;
	}
}
