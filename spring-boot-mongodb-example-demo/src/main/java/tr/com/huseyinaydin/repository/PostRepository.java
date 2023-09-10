package tr.com.huseyinaydin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import tr.com.huseyinaydin.model.Post;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

public interface PostRepository extends MongoRepository<Post, String>{

}