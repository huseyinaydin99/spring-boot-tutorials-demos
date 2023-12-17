package tr.com.huseyinaydin.swagger.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.swagger.api.model.Book;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot Swagger.
* 
*/

public interface BookRepository extends JpaRepository<Book, Integer> {

}