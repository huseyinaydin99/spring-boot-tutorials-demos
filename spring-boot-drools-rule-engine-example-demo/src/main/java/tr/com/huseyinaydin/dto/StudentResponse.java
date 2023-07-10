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
public class StudentResponse {

    @JsonProperty("grade")
    private String grade;

    @JsonProperty("gpa")
    private double gpa;

    @JsonProperty("desc")
    private String desc;
}
