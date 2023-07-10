package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot Examples
 * @since 1994
 **/

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
