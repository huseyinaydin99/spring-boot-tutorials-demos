package tr.com.huseyinaydin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_course")
public class Course implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "c_desc")
    private String desc;
    @Column(name = "price")
    private BigDecimal price;
}