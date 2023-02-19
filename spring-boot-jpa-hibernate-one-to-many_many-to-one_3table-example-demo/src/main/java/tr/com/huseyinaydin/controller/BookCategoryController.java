package tr.com.huseyinaydin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.com.huseyinaydin.model.BookCategory;
import tr.com.huseyinaydin.repository.BookCategoryRepository;

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
public class BookCategoryController {

    @Autowired
    BookCategoryRepository bookCategoryRepository;

    // http://localhost:8082/api/v1/books/categories
    @GetMapping("/books/categories")
    public List<BookCategory> getBooksAll() {
        return bookCategoryRepository.findAll();
    }

    // http://localhost:8082/api/v1/books/categories/1
    @GetMapping("/books/categories/{id}")
    public ResponseEntity<Optional<BookCategory>> getBookFindById(@PathVariable("id") Long id) {
        Optional<BookCategory> bookCategory = bookCategoryRepository.findById(id);
        return ResponseEntity.ok().body(bookCategory);
    }
}