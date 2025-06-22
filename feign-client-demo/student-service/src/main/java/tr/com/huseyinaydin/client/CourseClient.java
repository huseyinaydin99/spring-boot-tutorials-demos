package tr.com.huseyinaydin.client;

import tr.com.huseyinaydin.dto.Course;
import tr.com.huseyinaydin.dto.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@FeignClient(name = "course-client", url = "${application.services.course.url}")
public interface CourseClient {

    @GetMapping
    List<Course> courses();
    // CourseClient için bir proxy(vekil) oluşturur
    //RestTemplate -> isteği oluşturur

    @GetMapping("/{id}")
    Course course(@PathVariable int id);

    @PostMapping("/{id}/ratings")
    String submitRating(@PathVariable int id, @RequestBody Rating rating);
}