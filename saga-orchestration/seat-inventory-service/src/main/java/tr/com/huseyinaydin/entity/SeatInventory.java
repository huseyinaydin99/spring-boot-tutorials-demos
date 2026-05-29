package tr.com.huseyinaydin.entity;

import tr.com.huseyinaydin.utils.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot
 *
 */

@Entity
@Table(name = "seat_inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String theaterId;
    private String showId;
    private String seatNumber;
    private String screenId;

    @Enumerated(EnumType.STRING)
    private SeatStatus status; // AVAILABLE, LOCKED, RESERVED

    private String currentBookingId; // Eğer koltuk bu rezervasyon için kilitli/rezerve edilmişse

    private LocalDateTime lastUpdated;

    @PrePersist
    @PreUpdate
    public void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }
}
