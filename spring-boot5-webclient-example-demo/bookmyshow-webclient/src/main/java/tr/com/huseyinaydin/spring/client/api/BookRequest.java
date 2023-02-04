package tr.com.huseyinaydin.spring.client.api;

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
public class BookRequest {
	private int bookingId;
	private String userName;
	private String showName;
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING, timezone = "Europe/Turkey")
	private Date bookingTime;
	private int userCount;
	private double price;
}