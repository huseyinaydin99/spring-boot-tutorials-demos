package tr.com.huseyinaydin.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseFilterRequest extends PaginationRequest{

    private String name;
    private BigDecimal price;
}