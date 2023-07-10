package tr.com.huseyinaydin.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 **/

@Data
public class Address {
	@JsonProperty("city")
	private String city;
	
	@JsonProperty("street")
	private String street;
	
	@JsonProperty("number")
	private int number;
	
	@JsonProperty("zipcode")
	private String zipcode;
	
	@JsonProperty("geolocation")
	private Geolocation geolocation;

	@Data
	public static class Geolocation {
		@JsonProperty("lat")
		private double lat;
		
		@JsonProperty("long")
		private double longz;
	}
}