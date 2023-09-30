package tr.com.huseyinaydin.spring.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tr.com.huseyinaydin.spring.entity.Student;
import tr.com.huseyinaydin.spring.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
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
@Primary
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository repository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public Student addStudent(Student student) {
        return repository.save(student);
    }

    @Override
    public Optional<Student> getStudent(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Student> getStudents() {
        return repository.findAll();
    }
}