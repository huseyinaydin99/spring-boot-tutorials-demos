package tr.com.huseyinaydin.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import tr.com.huseyinaydin.mongodb.entity.Kullanici;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

public interface KullaniciRepository extends MongoRepository<Kullanici, String> {

}
