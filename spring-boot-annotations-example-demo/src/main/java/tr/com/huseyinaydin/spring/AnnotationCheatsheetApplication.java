package tr.com.huseyinaydin.spring;

import tr.com.huseyinaydin.spring.entity.Student;
import tr.com.huseyinaydin.spring.repository.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@SpringBootApplication
public class AnnotationCheatsheetApplication {

    private StudentRepository studentRepository;

    public AnnotationCheatsheetApplication(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostConstruct
    public void initStudents() {
        studentRepository.saveAll(Stream.of(
                        new Student(101, "Hüseyin", 14, "Hard Core Psycho Prorammer"),
                        new Student(102, "Rümeysa", 48, "Kuaför"),
                        new Student(103, "Yasin", 16, "Müdür"),
                        new Student(104, "Ömer Faruk", 12, "Öğrenci"))
                .collect(Collectors.toList()));
    }

    public static void main(String[] args) {
        SpringApplication.run(AnnotationCheatsheetApplication.class, args);
    }

}
