package tr.com.huseyinaydin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"requestId"})})
public class Payment {
    @Id
    @GeneratedValue
    private Long paymentId;

    private String requestId;
    private String orderId;
    private Double amount;
    private LocalDateTime createdAt;
}