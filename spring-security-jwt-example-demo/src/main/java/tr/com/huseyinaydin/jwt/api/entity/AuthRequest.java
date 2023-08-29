package tr.com.huseyinaydin.jwt.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String userName;
    private String password;
}
