package tr.com.huseyinaydin.ajax.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tr.com.huseyinaydin.ajax.api.dto.Book;
import tr.com.huseyinaydin.ajax.api.dto.ServiceResponse;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@RestController
public class BookController {

	List<Book> bookStore = new ArrayList<>();

	@PostMapping("/saveBook")
	public ResponseEntity<Object> addBook(@RequestBody Book book) {
		bookStore.add(book);
		ServiceResponse<Book> response = new ServiceResponse<Book>("success", book);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@GetMapping("/getBooks")
	public ResponseEntity<ServiceResponse> getAllBooks() {
		ServiceResponse<List<Book>> response = new ServiceResponse<List<Book>>("success", bookStore);
		return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
	}
}