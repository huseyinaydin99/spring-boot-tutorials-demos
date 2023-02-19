package tr.com.huseyinaydin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {

    private String field;
    private String errorMessage;
}