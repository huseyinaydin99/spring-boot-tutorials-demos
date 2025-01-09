package tr.com.huseyinaydin;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateAccountRequest {

	String name;
	String mobile;
	String email;
	String password;
	boolean remember;
}