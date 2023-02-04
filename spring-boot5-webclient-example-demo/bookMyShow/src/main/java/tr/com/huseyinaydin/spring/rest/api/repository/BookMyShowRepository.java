package tr.com.huseyinaydin.spring.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.spring.rest.api.model.BookRequest;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/


public interface BookMyShowRepository extends JpaRepository<BookRequest, Integer>{

}
