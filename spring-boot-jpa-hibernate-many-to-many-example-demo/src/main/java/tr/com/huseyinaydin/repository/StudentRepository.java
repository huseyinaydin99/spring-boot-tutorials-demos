package tr.com.huseyinaydin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.model.Student;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByNameContaining(String name);
}