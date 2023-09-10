package tr.com.huseyinaydin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tr.com.huseyinaydin.model.Post;
import tr.com.huseyinaydin.repository.PostRepository;
import tr.com.huseyinaydin.repository.SearchRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController
{

    @Autowired
    PostRepository repo;

    @Autowired
    SearchRepository srepo;

    @ApiIgnore
    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/allPosts")
    @CrossOrigin
    public List<Post> getAllPosts()
    {
        return repo.findAll();
    }
    // posts/java
    @GetMapping("/posts/{text}")
    @CrossOrigin
    public List<Post> search(@PathVariable String text)
    {
        return srepo.findByText(text);
    }

    @PostMapping("/post")
    @CrossOrigin
    public Post addPost(@RequestBody Post post)
    {
        return repo.save(post);
    }


}
