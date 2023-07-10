package tr.com.huseyinaydin;

import tr.com.huseyinaydin.model.Course;
import tr.com.huseyinaydin.model.Student;
import tr.com.huseyinaydin.repository.CourseRepository;
import tr.com.huseyinaydin.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot Examples
 * @since 1994
 **/

@SpringBootApplication
public class LearnSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnSpringApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    @Bean
    void loadData2() {
        Course course = this.course();
        Student student = this.student(course);
        course.setStudent(student);
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        list.forEach(i -> studentRepository.save(student));
    }

    protected Student student(Course course) {
        return new Student(0L, "Producer 1", "Consumer 1", "F", "2022-02-02", course);
    }

    protected Course course() {
        Course course = new Course();
        course.setId(0L);
        course.setPrice(45.0);
        course.setCourseName("Java");
        course.setCreateAt(new Date());
        return course;
    }

     /*  @Bean
    void loadData1() {
        Course course = this.course();
        Student student = studentRepository.save(this.student());
        course.setStudent(student);
        courseRepository.save(course);
    }
    */
    /* protected Student student() {
        return new Student(0L, "Producer 1", "Consumer 1", "F", "2022-02-02");
    }*/
}