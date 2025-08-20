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
 * @author Huseyin_Aydin
 * @category Java, Spring Boot.
 * @since 1994
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
    public ResponseEntity<?> getCourse(@PathVariable int id,
                                       @RequestHeader(name = "X-Request-Source") String sourceSystem) {
        return switch (sourceSystem.toLowerCase()) {
            case "udemy" -> (id == 2) ? fetchCourse(id)
                    : ResponseEntity.badRequest()
                    .body("Udemy'de " + id + " ID'li bir kurs bulunmamaktadır. Lütfen geçerli bir ID girin.");
            case "coursera" -> (id == 1) ? fetchCourse(id)
                    : ResponseEntity.badRequest()
                    .body("Coursera'da " + id + " ID'li bir kurs bulunmuyor. Lütfen geçerli bir ID girin.");
            case "unacademy" -> fetchCourse(id);
            default -> ResponseEntity.internalServerError()
                    .body("Bilinmeyen kaynak sistemi: " + sourceSystem);
        };
    }

    private ResponseEntity<Course> fetchCourse(int id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PostMapping("/{id}/ratings")
    public ResponseEntity<String> addRating(@PathVariable int id, @RequestBody Rating rating) {
        boolean added = courseService.addRating(id, rating);
        if (added) {
            return ResponseEntity.ok("Değerlendirme başarıyla gönderildi!");
        } else {
            return ResponseEntity.badRequest().body("Ders bulunamadı.");
        }
    }
}