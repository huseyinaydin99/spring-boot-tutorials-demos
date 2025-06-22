package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.dto.Course;
import tr.com.huseyinaydin.dto.Rating;
import tr.com.huseyinaydin.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable int id) {
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/ratings")
    public ResponseEntity<String> addRating(@PathVariable int id, @RequestBody Rating rating) {
        boolean added = courseService.addRating(id, rating);
        if (added) {
            return ResponseEntity.ok("Değerlendirme başarıyla gönderildi!");
        } else {
            return ResponseEntity.badRequest().body("Kurs bulunamadı.");
        }
    }
}