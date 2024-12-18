package tr.com.huseyinaydin.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String dept;
    private String email;
    private String gender;
    private double salary;

    public Employee(String name, String dept, String email, String gender, double salary) {
        this.name = name;
        this.dept = dept;
        this.email = email;
        this.gender = gender;
        this.salary = salary;
    }
}