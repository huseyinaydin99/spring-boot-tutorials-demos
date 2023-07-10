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
public class Name {
    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;
}