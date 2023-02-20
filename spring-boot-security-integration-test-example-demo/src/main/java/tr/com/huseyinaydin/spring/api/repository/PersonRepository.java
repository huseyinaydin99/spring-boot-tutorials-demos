package tr.com.huseyinaydin.spring.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.spring.api.entity.Person;

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 * 
 */

public interface PersonRepository extends JpaRepository<Person, Integer>{

}