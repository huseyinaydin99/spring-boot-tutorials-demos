package tr.com.huseyinaydin.vault.example;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@Data
@ConfigurationProperties("huseyinaydin")
public class Credential {

    private String username;
    private String password;
}
