package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot Examples
 * @since 1994
 **/

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
