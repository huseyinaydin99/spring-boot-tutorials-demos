package tr.com.huseyinaydin.domain.dto;

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
public class FoodOrder {
    private String orderId;
    private String customerName;
    private String restaurantName;
    private String item;
    private String status;

    // Constructors, Getters, Setters
}