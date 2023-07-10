package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot examples
 * @since 1994
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findFirstById(Long id);
}