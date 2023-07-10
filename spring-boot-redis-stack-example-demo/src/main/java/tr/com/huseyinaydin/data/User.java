package tr.com.huseyinaydin.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@Data
public class User {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("name")
    private Name name;

    @JsonProperty("address")
    private Address address;

    @JsonProperty("phone")
    private String phone;

}
