package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot examples
 * @since 1994
 */

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findFirstById(Long id);
}