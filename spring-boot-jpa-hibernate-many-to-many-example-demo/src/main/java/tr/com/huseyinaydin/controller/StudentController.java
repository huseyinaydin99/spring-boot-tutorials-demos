package tr.com.huseyinaydin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.com.huseyinaydin.model.Student;
import tr.com.huseyinaydin.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    // http://localhost:8082/api/v1/students
    @GetMapping("/students")
    public List<Student> getStudentsAll() {
        return studentRepository.findAll();
    }

    // http://localhost:8082/api/v1/students/1
    @GetMapping("/students/{id}")
    public ResponseEntity<Optional<Student>> getStudentFindById(@PathVariable("id") Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        return ResponseEntity.ok().body(student);
    }
}