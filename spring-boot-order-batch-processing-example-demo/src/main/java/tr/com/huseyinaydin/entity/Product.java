package tr.com.huseyinaydin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    private Long id;

    private String name;
    private String category;
    private double price;

    @Column(name = "isOfferApplied")
    private boolean isOfferApplied;

    @Column(name = "discountPercentage")
    private double discountPercentage;

    @Column(name = "priceAfterDiscount")
    private double priceAfterDiscount;
}