package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 @author Huseyin_Aydin
 @since 1994
 @category Spring Boot Examples
 *
 **/

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}