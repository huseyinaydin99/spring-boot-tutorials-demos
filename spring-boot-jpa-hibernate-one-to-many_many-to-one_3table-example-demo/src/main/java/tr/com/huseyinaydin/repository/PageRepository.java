package tr.com.huseyinaydin.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.model.Book;
import tr.com.huseyinaydin.model.Page;

import java.util.List;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public interface PageRepository extends JpaRepository<Page, Long> {
    List<Page> findByBook(Book book, Sort sort);
}