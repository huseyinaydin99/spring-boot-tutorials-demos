package tr.com.huseyinaydin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *
 @author Huseyin_Aydin
 @since 1994
 @category Spring Boot Examples
 *
 **/

@Data
public class StudentRequest {
    @JsonProperty("percentage_range")
    private double percentageRange;
}
