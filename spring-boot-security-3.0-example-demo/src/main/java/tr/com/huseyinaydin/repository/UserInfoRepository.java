package tr.com.huseyinaydin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.entity.UserInfo;

import java.util.Optional;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String username);
}