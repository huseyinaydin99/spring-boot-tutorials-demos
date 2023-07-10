package tr.com.huseyinaydin.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@Data
public class UserListResp {

    @JsonProperty("users")
    private List<User> users;
}
