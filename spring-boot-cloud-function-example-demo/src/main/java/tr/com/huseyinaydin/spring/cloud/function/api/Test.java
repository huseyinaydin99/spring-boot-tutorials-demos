package tr.com.huseyinaydin.spring.cloud.function.api;

import java.util.function.Function;

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 * 
 */

public class Test implements Function<String, String>{
	
	@Override
	public String apply(String t) {
		return "Serverless functional programming : example by "+t;
	}
}