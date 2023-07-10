package tr.com.huseyinaydin;

import tr.com.huseyinaydin.model.Book;
import tr.com.huseyinaydin.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot Examples
 * @since 1994
 */

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Autowired
    private BookRepository bookRepository;

    void saveBooks() {
        List<Book> books = Arrays.asList(
                new Book(0L, "Hero Book1", "Mr A", 29.00, new Date()),
                new Book(0L, "Hero Book2", "Mr A", 29.00, new Date()),
                new Book(0L, "Hero Book3", "Mr A", 29.00, new Date()),
                new Book(0L, "Hero Book4", "Mr A", 29.00, new Date()),
                new Book(0L, "Hero Book5", "Mr A", 29.00, new Date()),
                new Book(0L, "Hero Book6", "Mr A", 29.00, new Date()),
                new Book(0L, "Hero Book7", "Mr A", 29.00, new Date()),
                new Book(0L, "Hero Book8", "Mr A", 29.00, new Date()),
                new Book(0L, "Hero Book9", "Mr B", 29.00, new Date()),
                new Book(0L, "Hero Book10", "Mr B", 29.00, new Date()),
                new Book(0L, "Hero Book11", "Mr B", 29.00, new Date()),
                new Book(0L, "Hero Book12", "Mr B", 29.00, new Date()),
                new Book(0L, "Hero Book13", "Mr B", 29.00, new Date()),
                new Book(0L, "Hero Book14", "Mr B", 29.00, new Date()),
                new Book(0L, "Hero Book15", "Mr B", 29.00, new Date()),
                new Book(0L, "Hero Book16", "Mr B", 29.00, new Date()),
                new Book(0L, "Hero Book17", "Mr B", 29.00, new Date()),
                new Book(0L, "Hero Book18", "Mr B", 29.00, new Date()),
                new Book(0L, "Hero Book19", "Mr B", 29.00, new Date()),
                new Book(0L, "Hero Book20", "Mr B", 29.00, new Date()),
                new Book(0L, "Hero Book21", "Mr B", 29.00, new Date()),
                new Book(0L, "Hero Book22", "Mr B", 29.00, new Date())
        );
        bookRepository.saveAll(books);
    }

    @Bean
    void demoDerivedQuery() {
        this.saveBooks();
        List<Book> books = bookRepository.findAllByAuthor("Mr B");
    }
}