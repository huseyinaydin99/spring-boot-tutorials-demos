package tr.com.huseyinaydin.service;

import lombok.extern.slf4j.Slf4j;
import tr.com.huseyinaydin.model.Post;
import tr.com.huseyinaydin.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@Service
@Slf4j
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> posts() {
        return postRepository.findAll();
    }
}