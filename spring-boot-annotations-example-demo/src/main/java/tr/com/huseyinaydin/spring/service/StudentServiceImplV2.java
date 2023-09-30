package tr.com.huseyinaydin.spring.service;

import tr.com.huseyinaydin.spring.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Service
public class StudentServiceImplV2 implements StudentService{

    @Override
    public Student addStudent(Student student) {
        return null;
    }

    @Override
    public Optional<Student> getStudent(int id) {
        return Optional.empty();
    }

    @Override
    public List<Student> getStudents() {
        return null;
    }
}