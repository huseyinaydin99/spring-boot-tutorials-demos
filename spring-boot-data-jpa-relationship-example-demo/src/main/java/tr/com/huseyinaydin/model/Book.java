package tr.com.huseyinaydin.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot examples
 * @since 1994
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_book")
public class Book implements Serializable {


    private static final long serialVersionUID = -8434838863271172092L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    private String isbn;

    private Double rating;

    private String publishedDate;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Collection<PageClazz> pages;

    /**
     * the photo_id value must unique value in table tbl_book
     * because we use one to one
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Collection<Category> categories;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Collection<Author> authors;


}