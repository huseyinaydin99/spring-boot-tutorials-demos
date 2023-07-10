package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.dto.AuthorRequestDto;
import tr.com.huseyinaydin.dto.BookRequestDto;
import tr.com.huseyinaydin.dto.CategoryRequestDto;
import tr.com.huseyinaydin.dto.PhotoRequestDto;
import tr.com.huseyinaydin.service.AuthorService;
import tr.com.huseyinaydin.service.BookService;
import tr.com.huseyinaydin.service.CategoryService;
import tr.com.huseyinaydin.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot examples
 * @since 1994
 */

@Slf4j
@RestController
public class ApplicationController {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final PhotoService photoService;
    private final BookService bookService;

    public ApplicationController(AuthorService authorService, CategoryService categoryService, PhotoService photoService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.photoService = photoService;
        this.bookService = bookService;
    }

    @PostMapping("/photo/new")
    public ResponseEntity<Object> uploadNewPhoto(@RequestBody PhotoRequestDto photoRequestDto) {
        try {
            log.info("TRACING-REQUEST upload new photo with req: \n{}", photoRequestDto);
            photoService.addNewPhoto(photoRequestDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error while request upload new photo {}, \n{}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/category/new")
    public ResponseEntity<Object> createNewCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        try {
            log.info("TRACING-REQUEST create new category with req: \n{}", categoryRequestDto);
            categoryService.createNewCategory(categoryRequestDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error while request create new category {}, \n{}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/author/add")
    public ResponseEntity<Object> addAuthor(@RequestBody AuthorRequestDto authorRequestDto) {
        try {
            log.info("TRACING-REQUEST add author with req: \n{}", authorRequestDto);
            authorService.add(authorRequestDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error while request add author {}, \n{}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/book/new")
    public ResponseEntity<Object> createNewBook(@RequestBody BookRequestDto bookRequestDto) {
        try {
            log.info("TRACING-REQUEST create new book with req: \n{}", bookRequestDto);
            bookService.createNewBook(bookRequestDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error while request create new book {}, \n{}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/book/list")
    public ResponseEntity<Object> getAllBooks() {
        try {
            log.info("TRACING-REQUEST get all books");
            return new ResponseEntity<>(bookService.listBooks(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while get all books {}, \n{}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}