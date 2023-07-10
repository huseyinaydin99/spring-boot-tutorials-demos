package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot examples
 * @since 1994
 */

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Optional<Photo> findFirstById(Long id);
}