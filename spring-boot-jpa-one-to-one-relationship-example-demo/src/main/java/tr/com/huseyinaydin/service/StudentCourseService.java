package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.CourseDto;
import tr.com.huseyinaydin.dto.StudentCourseDto;
import tr.com.huseyinaydin.model.Course;
import tr.com.huseyinaydin.model.Student;
import tr.com.huseyinaydin.repository.CourseRepository;
import tr.com.huseyinaydin.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot Examples
 * @since 1994
 **/

@Service
@RequiredArgsConstructor
public class StudentCourseService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public List<StudentCourseDto> getAllStudentsCourse() {
        return studentRepository
                .findAll()
                .stream()
                .map(this::convertToStudentCourseDtoWithModelMapper)
                .collect(Collectors.toList());
    }

    private StudentCourseDto convertToStudentCourseDtoWithModelMapper(Student student) {
        return modelMapper
                .map(student, StudentCourseDto.class);
    }

    public List<CourseDto> getAllCourses() {
        return courseRepository
                .findAll()
                .stream()
                .map(this::convertToStudentCourseDto)
                .collect(Collectors.toList());
    }

    private CourseDto convertToStudentCourseDto(Course course) {
        return modelMapper
                .map(course, CourseDto.class);
    }
}
