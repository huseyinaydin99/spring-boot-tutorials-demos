package tr.com.huseyinaydin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputRequest {

	private int id;
	private String firstName;
	private String lastName;
}