package tr.com.huseyinaydin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.com.huseyinaydin.model.Book;
import tr.com.huseyinaydin.repository.BookRepository;

import java.util.List;
import java.util.Optional;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@RestController
@RequestMapping("/api/v1")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    // http://localhost:8082/api/v1/books
    @GetMapping("/books")
    public List<Book> getBooksAll() {
        return bookRepository.findAll();
    }

    // http://localhost:8082/api/v1/books/1
    @GetMapping("/books/{id}")
    public ResponseEntity<Optional<Book>> getBookFindById(@PathVariable("id") Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return ResponseEntity.ok().body(book);
    }
}