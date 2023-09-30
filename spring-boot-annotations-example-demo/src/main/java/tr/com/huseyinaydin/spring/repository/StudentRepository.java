package tr.com.huseyinaydin.spring.repository;

import tr.com.huseyinaydin.spring.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}