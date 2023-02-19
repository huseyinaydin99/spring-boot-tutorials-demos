package tr.com.huseyinaydin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tr.com.huseyinaydin.model.Course;
import tr.com.huseyinaydin.model.Student;
import tr.com.huseyinaydin.repository.CourseRepository;
import tr.com.huseyinaydin.repository.StudentRepository;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
    
    @Transactional
    @Override
    public void run(String... arg0) throws Exception {
       
    	Student student1 = new Student("Hasan");
        Student student2 = new Student("Hüseyin");
        Student student3 = new Student("Ahmet");
        Student student4 = new Student("Samet");
        Student student5 = new Student("Murat");

        Course course1 = new Course("Machine Learning");
        Course course2 = new Course("Database Systems");
        Course course3 = new Course("Java Technologies");

		/*
		courseRepository.save(course1);
		courseRepository.save(course2);
		*/

        Set<Course> courses = new HashSet<Course>();
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);

        student1.setCourses(courses);
        student2.setCourses(courses);
        student3.setCourses(courses);
        student4.setCourses(courses);
        student5.setCourses(courses);

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);
        studentRepository.save(student5);

        Set<Student> studentsGoup1 = new HashSet<Student>();
        studentsGoup1.add(student1);
        studentsGoup1.add(student2);
        
        Set<Student> studentsGoup2 = new HashSet<Student>();
        studentsGoup2.add(student3);
        studentsGoup2.add(student4);
        studentsGoup2.add(student5);

        course1.setStudents(studentsGoup1);
        course2.setStudents(studentsGoup1);
        course3.setStudents(studentsGoup2);

        courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);

        List<Student> studentLst = studentRepository.findAll();
        List<Course> subLst = courseRepository.findAll();

        System.out.println(studentLst.size());
        System.out.println(subLst.size());

        System.out.println("===================Students info:==================");
        studentLst.forEach(student->System.out.println(student.toString()));

        System.out.println("===================Students info:==================");
        subLst.forEach(course->System.out.println(course.toString()));
    }
    
    
    // TODO - I will do it
    /*
    @Bean
    public CommandLineRunner mappingDemo(StudentRepository studentRepository, CourseRepository courseRepository) {
        return args -> {

            // create a student
            Student student = new Student("Hüseyin", 15, "8th");

            // save the student
            studentRepository.save(student);

            // create three courses
            Course course1 = new Course("Machine Learning", "ML", 13, 900);
            Course course2 = new Course("Database Systems", "DS", 9, 600);
            Course course3 = new Course("Java Technologies", "JT", 11, 0);

            // save courses
            courseRepository.saveAll(Arrays.asList(course1, course2, course3));

            // add courses to the student
            student.getCourses().addAll(Arrays.asList(course1, course2, course3));

            // update the student
            studentRepository.save(student);
        };
    }
    */
}