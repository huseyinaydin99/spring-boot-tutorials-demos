package tr.com.huseyinaydin.adapter.output.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Entity
@Table(name = "orders")
@Data
public class OrderEntity {

    @Id
    private String orderId;

    private String customerName;
    private String restaurantName;
    private String item;
    private String status;

    // Getters & Setters
}