package tr.com.huseyinaydin.data;

import lombok.Data;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@Data
public class Product {

    private Integer id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
