package tr.com.huseyinaydin.repository;

import java.util.List;

import tr.com.huseyinaydin.model.Post;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

public interface SearchRepository {
    List<Post> findByText(String text);
}