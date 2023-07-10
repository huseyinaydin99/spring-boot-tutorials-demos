package tr.com.huseyinaydin;

import jakarta.annotation.PostConstruct;
import tr.com.huseyinaydin.model.Post;
import tr.com.huseyinaydin.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@SpringBootApplication
public class SpringBootRatingLimitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRatingLimitApplication.class, args);
	}

	@Autowired
	PostRepository postRepository;

	@PostConstruct
	void initData() {
		List<Post> posts = Arrays.asList(
				new Post(0L, "Java", "Java Testing", new Date(), "Hüseyin"),
				new Post(0L, "Spring Boot", "Spring Boot", new Date(), "Samet")
		);
		postRepository.saveAll(posts);
	}
}