package tr.com.huseyinaydin.client;

import tr.com.huseyinaydin.dto.Course;
import tr.com.huseyinaydin.dto.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/*
Feign Client sayesinde RestTemplate ile uğraştığım uzun uzun URL kurma,
request/response yönetme ve manuel konfigürasyon işlerini ortadan kaldırdım 🚀;
sadece endpoint’i tanımlayıp işi Feign’e bırakarak kodu daha sade,
okunabilir ve bakımı kolay hale getirdim 📚, ayrıca merkezi konfigürasyon ⚙️,
dinamik servis keşfi 🔍 ve kolay test edilebilirlik 🧪
gibi avantajlarla mikroservisler arası iletişimi çok daha güçlü, esnek ve yönetilebilir ettim 🎯.
*/

@Service
public class CourseRestClientService {

    private static final String COURSE_BASE_URL = "http://localhost:8081/api/courses";

    @Autowired
    private RestTemplate restTemplate;

    public Course getCourseById(int id) {
        String url = COURSE_BASE_URL + "/" + id;
        ResponseEntity<Course> response = restTemplate
                .getForEntity(url, Course.class);
        return response.getBody();
    }

    public List<Course> getAllCourses() {
        ResponseEntity<Course[]> response = restTemplate
                .getForEntity(COURSE_BASE_URL, Course[].class);
        return Arrays.asList(response.getBody() != null
                ? response.getBody() : null);
    }

    public String submitCourseRating(Integer courseId, Rating rating) {
        String url = COURSE_BASE_URL + "/" + courseId + "/ratings";
        return restTemplate.postForObject(url, rating, String.class);
    }
}