package tr.com.huseyinaydin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot.
 * @since 1994
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private int id;
    private String name;
    private String email;
    private String number;
}
