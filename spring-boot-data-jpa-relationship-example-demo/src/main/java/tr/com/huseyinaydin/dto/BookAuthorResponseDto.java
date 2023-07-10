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
public class BookAuthorResponseDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("rating")
    private Double rating;

    @JsonProperty("published_date")
    private String publishedDate;

    @JsonProperty("author_detail")
    private AuthorDto authorDto;

    @JsonProperty("page_detail")
    private PageDto pageDto;

    @JsonProperty("category_detail")
    private CategoryDto categoryDto;

    @Data
    public static class PageDto {
        @JsonProperty("total_pages")
        private int totalPages;

        @JsonProperty("total_chapters")
        private int chapter;
    }

    @Data
    public static class CategoryDto {
        @JsonProperty("category")
        private String category;

        @JsonProperty("description")
        private String description;
    }

    @Data
    public static class AuthorDto {
        @JsonProperty("first_name")
        private String firstName;

        @JsonProperty("last_name")
        private String lastName;

        @JsonProperty("gender")
        private String gender;

        @JsonProperty("birth_date")
        private String birthDate;

        @JsonProperty("nationality")
        private String nationality;

        @JsonProperty("phone_number")
        private String phoneNumber;

        @JsonProperty("email")
        private String email;
    }
}
