package tr.com.huseyinaydin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot examples
 * @since 1994
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_photo")
public class Photo implements Serializable {

    private static final long serialVersionUID = -5013912991983550983L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long id;

    private String smallUrl;

    private String mediumUrl;

    private String largeUrl;

    private String description;

    @OneToOne(mappedBy = "photo")
    private Book book;
}