package tr.com.huseyinaydin.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 */

@Data
@Builder
public class CourseResponsePagination {

    @JsonProperty("courses")
    private List<CourseResponse> courseResponses;

    @JsonProperty("page_detail")
    private PaginationResponse paginationResponse;
}