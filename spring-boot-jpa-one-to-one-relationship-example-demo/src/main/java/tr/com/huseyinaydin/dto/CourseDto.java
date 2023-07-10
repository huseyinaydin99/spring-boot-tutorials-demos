package tr.com.huseyinaydin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot Examples
 * @since 1994
 **/

@Data
public class CourseDto {

    @JsonProperty("course_name")
    private String courseName;

    @JsonProperty("course_price")
    private double price;

    @JsonProperty("create_at")
    private Date createAt;
}