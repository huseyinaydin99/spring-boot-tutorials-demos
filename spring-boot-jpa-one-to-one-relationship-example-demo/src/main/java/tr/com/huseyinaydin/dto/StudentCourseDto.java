package tr.com.huseyinaydin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot Examples
 * @since 1994
 **/

@Data
public class StudentCourseDto {

    @JsonProperty("student_id")
    private long studentId;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("dob")
    private String dob;

    @JsonProperty("course_name")
    private String courseName;

    @JsonProperty("course_price")
    private double price;
}