package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.dto.CourseDto;
import tr.com.huseyinaydin.dto.StudentCourseDto;
import tr.com.huseyinaydin.service.StudentCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot Examples
 * @since 1994
 **/

@RestController
@RequiredArgsConstructor
public class StudentCourseController {

    private final StudentCourseService studentCourseService;

    @GetMapping("students")
    public List<StudentCourseDto> getAllUsersLocation() {
        return studentCourseService.getAllStudentsCourse();
    }

    @GetMapping("lazy-eager")
    public List<CourseDto> courses() {
        return studentCourseService.getAllCourses();
    }
}