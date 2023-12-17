package tr.com.huseyinaydin.swagger.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import tr.com.huseyinaydin.swagger.api.model.Book;
import tr.com.huseyinaydin.swagger.api.service.BookService;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot Swagger.
* 
*/

@RestController
@RequestMapping("/book")
@Api(value = "Kitap Servisi", description = "Kitapçı Dükkanı")
public class BookController {

	@Autowired
	private BookService service;

	@PostMapping("/save")
	@ApiOperation(value = "Kitap kaydı metodu.")
	public String saveBook(@RequestBody Book book) {
		return service.saveBook(book);
	}

	@ApiOperation(value = "Kitap arama metodu. İstenilen parametreyi gir kitabın kaydını al.")
	@GetMapping("/searchBook/{bookId}")
	public Book getBook(@PathVariable int bookId) {
		return service.getBook(bookId);
	}

	@ApiOperation(value = "Kitap silme metodu. İstenilen parametreyi gir kitabın kaydını sil.")
	@DeleteMapping("/deleteBook/{bookId}")
	public List<Book> deleteBook(@PathVariable int bookId) {
		return service.removeBook(bookId);

	}
}