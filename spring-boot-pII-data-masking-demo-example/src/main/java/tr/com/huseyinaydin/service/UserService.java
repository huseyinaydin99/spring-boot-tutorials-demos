package tr.com.huseyinaydin.service;

import org.springframework.stereotype.Service;

import tr.com.huseyinaydin.dto.AccountDetails;
import tr.com.huseyinaydin.dto.User;

import java.util.Arrays;
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

@Service
public class UserService {

    public List<User> getAllUsers() {

        User user1 = new User(3235, "Hüseyin", "6474729899", "1234567890", 30, "husos", "huso123",
                List.of(new AccountDetails("Hüseyin Aydın", "365027332671", "SAVING")
                        , new AccountDetails("Samet Ünlü", "365027332671", "CURRENT")));

        User user2 = new User(9546, "Semih", "4623642828", "5439854674", 41, "semihkn", "semih123",
                List.of(new AccountDetails("Semih Ünlü", "8272389200", "SAVING")));

        return Stream.of(user1, user2).collect(Collectors.toList());
    }

    public User getUser(int id) {
        return getAllUsers().stream().filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("user not found"));
    }
}
