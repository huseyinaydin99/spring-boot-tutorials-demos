package tr.com.huseyinaydin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.entity.UserCredential;

import java.util.Optional;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

public interface UserCredentialRepository  extends JpaRepository<UserCredential,Integer> {
    Optional<UserCredential> findByName(String username);
}