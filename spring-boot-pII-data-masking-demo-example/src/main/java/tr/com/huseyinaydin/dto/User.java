package tr.com.huseyinaydin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.huseyinaydin.encrypt.MaskData;

import java.util.List;

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
@Builder
public class User {

    private int id;
    private String name;
    @MaskData
    private String ssn;
    @MaskData
    private String phoneNumber;
    private int age;
    private String username;
    @MaskData
    private String password;
    private List<AccountDetails> accountDetails;
}
