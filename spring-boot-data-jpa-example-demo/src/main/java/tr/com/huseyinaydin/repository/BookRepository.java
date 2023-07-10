package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot Examples
 * @since 1994
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAuthor(String author);

    int countByAuthor(String author);

    List<Book> findDistinctByAuthor(String author);

    List<Book> findAllByAuthorAndTitle(String author, String title);

    List<Book> findAllByAuthorOrTitle(String author, String title);

    List<Book> findByAuthorIs(String author);

    List<Book> findByAuthorEquals(String author);

    List<Book> findByAuthorIsNot(String author);

    List<Book> findByAuthorNot(String author);

    List<Book> findByAuthorStartingWith(String author);

    List<Book> findByAuthorEndingWith(String author);

    List<Book> findByAuthorContaining(String author);

    List<Book> findByPriceGreaterThan(double price);

    List<Book> findByPriceLessThan(double price);

    List<Book> findByPriceBetween(double price1, double price2);

    List<Book> findByAuthorOrderByTitle(String author); //List<Book> findByAuthorOrderByTitleAsc(String author);

    List<Book> findByAuthorOrderByTitleDesc(String author);

    Optional<Book> findFirstByAuthor(String author);

    List<Book> findFirst10ByAuthor(String author);

    List<Book> findTop10ByTitle(String title);
}