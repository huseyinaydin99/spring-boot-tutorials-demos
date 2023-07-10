package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.dto.StudentRequest;
import tr.com.huseyinaydin.dto.StudentResponse;
import tr.com.huseyinaydin.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 @author Huseyin_Aydin
 @since 1994
 @category Spring Boot Examples
 *
 **/

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("grade")
    public ResponseEntity<Object> getStudentGrade(@RequestBody StudentRequest studentRequest) {
        StudentResponse studentResponse = studentService.studentResponse(studentRequest);
        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }
}
