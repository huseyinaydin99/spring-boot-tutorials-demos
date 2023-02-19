package tr.com.huseyinaydin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.model.Course;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByTitleContaining(String title);
    List<Course> findByFeeLessThan(double fee);
}