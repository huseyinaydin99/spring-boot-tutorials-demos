package tr.com.huseyinaydin.cache.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.cache.api.model.User;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot Hazlecast Cache.
 *
 */

public interface UserRepository extends JpaRepository<User, Integer> {
}