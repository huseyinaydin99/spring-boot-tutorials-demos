package tr.com.huseyinaydin.dto;

import lombok.Data;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@Data
public class Customer {

    private int id;
    private String name;
    private String email;
    private String contactNo;
}