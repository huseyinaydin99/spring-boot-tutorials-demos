package tr.com.huseyinaydin.ajax.api.dto;

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
public class Book {

	private int bookId;
	private String bookName;
	private String author;
}