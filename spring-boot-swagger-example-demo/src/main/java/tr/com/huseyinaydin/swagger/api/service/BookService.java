package tr.com.huseyinaydin.swagger.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.huseyinaydin.swagger.api.dao.BookRepository;
import tr.com.huseyinaydin.swagger.api.model.Book;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot Swagger.
* 
*/

@Service
public class BookService {
	@Autowired
	private BookRepository repository;

	public String saveBook(Book book) {
		repository.save(book);
		return "Kitap kaydedildi. Id: " + book.getBookId();
	}

	public Book getBook(int bookId) {
		return repository.findOne(bookId);
	}

	public List<Book> removeBook(int bookId) {
		repository.delete(bookId);
		return repository.findAll();
	}
}