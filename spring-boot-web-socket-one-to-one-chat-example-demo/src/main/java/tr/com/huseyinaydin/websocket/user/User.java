package tr.com.huseyinaydin.websocket.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Getter
@Setter
@Document
public class User {
    @Id
    private String nickName;
    private String fullName;
    private Status status;
}