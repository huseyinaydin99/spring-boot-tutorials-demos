package tr.com.huseyinaydin.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 **/

@Document
@Data
@AllArgsConstructor
@Builder
public class Category {

	@Id
	private String id;
	private String name;
	private String description;
}
