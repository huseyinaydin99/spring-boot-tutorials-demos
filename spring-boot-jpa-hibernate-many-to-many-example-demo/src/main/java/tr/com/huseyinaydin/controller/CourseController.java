package tr.com.huseyinaydin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.com.huseyinaydin.model.Course;
import tr.com.huseyinaydin.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@RestController
@RequestMapping("/api/v1")
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    // http://localhost:8082/api/v1/courses
    @GetMapping("/courses")
    public List<Course> getCoursesAll() {
        return courseRepository.findAll();
    }


    // http://localhost:8082/api/v1/courses/1
    @GetMapping("/courses/{id}")
    public ResponseEntity<Optional<Course>> getCourseFindById(@PathVariable("id") Integer id) {
        Optional<Course> course = courseRepository.findById(id);
        return ResponseEntity.ok().body(course);
    }
}