package tr.com.huseyinaydin.websocket.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByStatus(Status status);
}