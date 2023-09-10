package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.entity.Course;
import tr.com.huseyinaydin.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@AllArgsConstructor
@Slf4j
public class CourseService {

    private CourseRepository courseRepository;

    public Course addNewCourse(Course course) {
        log.info("Kurs servisi: kurs eklendi.");
        return courseRepository.save(course);
    }

    public List<Course> getAllAvailableCourses() {
        log.info("Kurs servisi: tüm kullanılabilir kurslar eklendi.");
        return courseRepository.findAll();
    }
}