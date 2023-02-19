package tr.com.huseyinaydin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tr.com.huseyinaydin.model.Book;
import tr.com.huseyinaydin.model.Page;
import tr.com.huseyinaydin.repository.BookRepository;
import tr.com.huseyinaydin.repository.PageRepository;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@SpringBootApplication
public class MainApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner mappingDemo(BookRepository bookRepository, 
    									 PageRepository pageRepository) {
        return args -> {
       
            
        	// create a new book
            Book book1 = new Book("Java 99", "Hüseyin Aydın", "9876543210-1");

            // save the book
            bookRepository.save(book1);

            // create and save new pages
            pageRepository.save(new Page(1, "Introduction contents", "Introduction", book1));
            pageRepository.save(new Page(14, "Java contents", "Java", book1));
            pageRepository.save(new Page(53, "Concurrency contents", "Concurrency", book1));
            
            
            // create a new book
            Book book2 = new Book("Scala 98", "Hasan Aydın", "987654");

            // save the book
            bookRepository.save(book2);

            // create and save new pages
            pageRepository.save(new Page(1, "PART 1", "Scala", book2));
            pageRepository.save(new Page(20, "PART 2", "Play", book2));
            pageRepository.save(new Page(60, "PART 3", "Akka", book2));

        };
    }
}