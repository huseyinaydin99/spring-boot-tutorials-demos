package tr.com.huseyinaydin.entity;

import jakarta.persistence.*;
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
@Data
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String movieName;

    private boolean booked;

    @Version
    private int version;
}