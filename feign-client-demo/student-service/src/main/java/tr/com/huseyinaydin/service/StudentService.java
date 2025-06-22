package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.client.CourseClient;
import tr.com.huseyinaydin.dto.Course;
import tr.com.huseyinaydin.dto.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class StudentService {

//  @Autowired
//  private CourseRestClientService courseClient;

    @Autowired
    private CourseClient courseClient;

    public Course searchCourse(int courseId) {
        return courseClient.course(courseId);
    }

    public List<Course> getAllCourses() {
        return courseClient.courses();
    }

    public String submitRating(int courseId, Rating rating) {
        return courseClient.submitRating(courseId, rating);
    }
}