package tr.com.huseyinaydin.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 */

@Data
@Builder
public class CourseRequest {

    private Long id;

    @JsonProperty("name")
    private String name;
    @JsonProperty("c_desc")
    private String desc;
    @JsonProperty("price")
    private BigDecimal price;
}
