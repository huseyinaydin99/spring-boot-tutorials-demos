package tr.com.huseyinaydin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot examples
 * @since 1994
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {

    @JsonProperty("category")
    private String category;

    @JsonProperty("description")
    private String description;
}