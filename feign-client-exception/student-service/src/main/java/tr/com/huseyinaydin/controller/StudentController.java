package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.dto.Course;
import tr.com.huseyinaydin.dto.Rating;
import tr.com.huseyinaydin.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/search/{courseId}")
    public Course search(@PathVariable int courseId, @RequestParam("sourceSystem") String sourceSystem) {
        return studentService.searchCourse(courseId,sourceSystem);
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return studentService.getAllCourses();
    }

    @PostMapping("/courses/{courseId}/ratings")
    public String submitRating(@PathVariable int courseId, Rating rating) {
        return studentService.submitRating(courseId, rating);
    }
}