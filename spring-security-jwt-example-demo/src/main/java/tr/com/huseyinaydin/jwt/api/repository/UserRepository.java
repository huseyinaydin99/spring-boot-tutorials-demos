package tr.com.huseyinaydin.jwt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.jwt.api.entity.User;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String username);
}