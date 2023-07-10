package tr.com.huseyinaydin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 4715702306723909037L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String employeeNo;

    private String firstName;

    private String midInit;

    private String lastName;

    private String workDept;

    private String phoneNo;

    private Date hireDate;

    private String job;

    private String edLevel;

    private String sex;

    private Date birthDate;

    private BigDecimal salary;

    private Double bonus;

    private Double commission;
}