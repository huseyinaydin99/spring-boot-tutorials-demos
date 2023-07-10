package tr.com.huseyinaydin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot examples
 * @since 1994
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(content = JsonInclude.Include.NON_NULL)
public class BookRequestDto {

    @JsonProperty("title")
    private String title;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("rating")
    private Double rating;

    @JsonProperty("published_date")
    private String publishedDate;

    @JsonProperty("author_id")
    private Long authorId;

    @JsonProperty("photo_id")
    private Long photoId;

    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("page_detail")
    private PageDto pageDto;

    @Data
    public static class PageDto {
        @JsonProperty("total_pages")
        private int totalPages;

        @JsonProperty("total_chapters")
        private int chapter;
    }
}