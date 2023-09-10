package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.entity.Course;
import tr.com.huseyinaydin.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@AllArgsConstructor
@RequestMapping("/courses")
@Slf4j
public class CourseController {

    private CourseService courseService;

    @PostMapping
    public Course addCourse(@RequestBody Course course) {
        log.info("CourseController::addCourse method executed");
        return courseService.addNewCourse(course);
    }

    @GetMapping
    public List<Course> viewAllCourses() {
        log.info("CourseController::viewAllCourses method executed");
        return courseService.getAllAvailableCourses();
    }
}