package tr.com.huseyinaydin.multiple.ds.api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.com.huseyinaydin.multiple.ds.api.model.user.User;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}