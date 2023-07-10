package tr.com.huseyinaydin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 @author Huseyin_Aydin
 @since 1994
 @category Spring Boot Examples
 *
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int id;
    private String username;
    private String password;
    private Date crateAt;
}