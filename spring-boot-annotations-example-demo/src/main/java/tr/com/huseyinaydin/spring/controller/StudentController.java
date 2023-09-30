package tr.com.huseyinaydin.spring.controller;

import tr.com.huseyinaydin.spring.LazyLoadingBean;
import tr.com.huseyinaydin.spring.TestBean;
import tr.com.huseyinaydin.spring.config.MailProps;
import tr.com.huseyinaydin.spring.entity.Student;
import tr.com.huseyinaydin.spring.exception.StudentNotFoundException;
import tr.com.huseyinaydin.spring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

@RestController
@RequestMapping("/students")
@PropertySource("classpath:custom.properties")
@Scope("prototype")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TestBean testBean;

    @Autowired
    private LazyLoadingBean lazyLoadingBean;

    @Value("${mail.from}")
    private String from;
    @Value("${mail.host}")
    private String host;
    @Value("${mail.port}")
    private String port;
    @Value("${message}")
    private String message;

    @Autowired
    private MailProps mailProps;

    public StudentController() {
        System.out.println("Kontrolör objesi var edilidi.");
    }

    @PostMapping("/save")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.addStudent(student));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> getStudent(@PathVariable int id) throws StudentNotFoundException {
        Optional<Student> student = studentService.getStudent(id);
        if (student.isPresent()) {
            return ResponseEntity.ok(student);
        } else {
            throw new StudentNotFoundException("Öğrenci bulunamadı. ID'si: " + id);
        }

    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getStudents() {
        System.out.println("Mail özellikleri : "+ mailProps);
        return ResponseEntity.ok(studentService.getStudents());
    }
}