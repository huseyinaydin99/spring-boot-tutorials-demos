package tr.com.huseyinaydin.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot Examples
 * @since 1994
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_book")
public class Book implements Serializable {

    private static final long serialVersionUID = 5581693298900316178L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "price")
    private Double price;

    @Column(name = "publish_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date publishDate;
}