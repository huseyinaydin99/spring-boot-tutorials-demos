package tr.com.huseyinaydin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Integer id;
    private String name;
    private String instructor;
    private List<Rating> ratings = new ArrayList<>();

    public double getAverageRating() {
        if (ratings.isEmpty()) return 0;
        return ratings.stream().mapToInt(Rating::getStars).average().orElse(0);
    }
}