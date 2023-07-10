package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 *
 @author Huseyin_Aydin
 @since 1994
 @category Spring Boot Examples
 *
 **/

@RepositoryRestResource(path = "books")
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAuthor(@Param("author") String author);
}
