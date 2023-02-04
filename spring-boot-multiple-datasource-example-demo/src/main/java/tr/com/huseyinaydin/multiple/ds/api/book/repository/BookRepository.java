package tr.com.huseyinaydin.multiple.ds.api.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.com.huseyinaydin.multiple.ds.api.model.book.Book;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}