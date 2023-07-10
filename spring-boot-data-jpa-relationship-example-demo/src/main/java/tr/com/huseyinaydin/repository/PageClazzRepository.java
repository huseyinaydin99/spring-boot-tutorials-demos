package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.model.PageClazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot examples
 * @since 1994
 */

@Repository
public interface PageClazzRepository extends JpaRepository<PageClazz, Long> {

}