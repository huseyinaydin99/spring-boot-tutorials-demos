package tr.com.huseyinaydin.spring.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Entity
@Table(name = "STUDENT_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @Column(name = "STUDENT_ID")
    private int id;
    @Column(name = "STUDENT_NAME")
    private String name;
    @Column(name = "ROLL_NO")
    private int rollNo;
    @Column(name = "DEPT")
    private String dept;

    @OneToMany(targetEntity = Course.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "Student_FK")
    private List<Course> courses;
}