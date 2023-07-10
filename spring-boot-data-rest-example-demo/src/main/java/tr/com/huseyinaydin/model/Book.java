package tr.com.huseyinaydin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 @author Huseyin_Aydin
 @since 1994
 @category Spring Boot Examples
 *
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_name")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String description;
    @Column(name = "category_ide")
    private Integer categoryId;

    @Column(name = "published_date")
    private String publishedDate;
}
