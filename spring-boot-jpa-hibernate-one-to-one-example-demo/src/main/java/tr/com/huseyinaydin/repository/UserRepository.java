package tr.com.huseyinaydin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.model.User;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public interface UserRepository extends JpaRepository<User, Long> {

}