package tr.com.huseyinaydin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.dto.Course;
import tr.com.huseyinaydin.dto.Rating;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Service
public class CourseService {

    private List<Course> courses_ = new ArrayList<>();

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = new ClassPathResource("courses.json").getInputStream()) {
            Course[] courses = mapper.readValue(is, Course[].class);
            courses_ = new ArrayList<>(Arrays.asList(courses));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<Course> getCourseById(int id) {
        return courses_.stream()
                .filter(course -> course.getId() == id)
                .findFirst();
    }

    public List<Course> getAllCourses() {
        return courses_;
    }

    public boolean addRating(int courseId, Rating rating) {
        Course course = getCourseById(courseId)
                .orElse(null);
        if (course != null) {
            course.getRatings().add(rating);
            return true;
        }
        return false;
    }
}