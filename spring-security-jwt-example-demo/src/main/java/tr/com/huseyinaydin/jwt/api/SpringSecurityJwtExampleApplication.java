package tr.com.huseyinaydin.jwt.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tr.com.huseyinaydin.jwt.api.entity.User;
import tr.com.huseyinaydin.jwt.api.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@SpringBootApplication
public class SpringSecurityJwtExampleApplication {
    @Autowired
    private UserRepository repository;

    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User(101, "huso", "123456Aa*", "huseyinaydin99@gmail.com"),
                new User(102, "user1", "123_", "user1@gmail.com"),
                new User(103, "user2", "123#", "user2@gmail.com"),
                new User(104, "user3", "123!", "user3@gmail.com")
        ).collect(Collectors.toList());
        repository.saveAll(users);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtExampleApplication.class, args);
    }
}