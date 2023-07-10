package tr.com.huseyinaydin.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 */

@Data
@Builder
public class PaginationResponse {

    @JsonProperty("total_pages")
    private int totalPage;

    @JsonProperty("current")
    private int current;

    @JsonProperty("size")
    private int size;

    @JsonProperty("records")
    private long records;
}