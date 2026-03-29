package tr.com.huseyinaydin.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    //@ElementCollection, entity içinde primitive veya @Embeddable türündeki koleksiyon verilerini tutmak için otomatik olarak ayrı bir tablo oluşturan JPA anotasyonudur.
    @ElementCollection
    private List<OrderItem> items = new ArrayList<>();

    //customer -> address1, address2
    //order -> item1, item2, item3 , item4
}