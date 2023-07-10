package tr.com.huseyinaydin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_name")
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String desc;
    private Date postedDate;
    private String postedBy;
}
