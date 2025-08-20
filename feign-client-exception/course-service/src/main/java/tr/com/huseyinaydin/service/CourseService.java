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

    private List<Course> COURSES = new ArrayList<>();

    @PostConstruct //yapıcı metot sonrası devreye girer
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = new ClassPathResource("courses.json").getInputStream()) {
            Course[] courses = mapper.readValue(is, Course[].class);
            COURSES = new ArrayList<>(Arrays.asList(courses));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Course getCourseById(int id) {
        return COURSES.stream()
                .filter(course -> course.getId() == id)
                .findFirst().orElseThrow(()-> new RuntimeException(id + " ID'li kurs bulunamadı!"));
    }

    public List<Course> getAllCourses() {
        return COURSES;
    }

    public boolean addRating(int courseId, Rating rating) {
        Course course = getCourseById(courseId);
        if (course != null) {
            course.getRatings().add(rating);
            return true;
        }
        return false;
    }
}