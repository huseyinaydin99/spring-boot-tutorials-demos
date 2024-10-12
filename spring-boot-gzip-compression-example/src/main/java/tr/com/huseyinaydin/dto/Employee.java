package tr.com.huseyinaydin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer employeeId;
    private String employeeName;
    private String employeeContactNumber;
    private String employeeAddress;
    private String employeeGender;
    private String employeeDepartment;
    private String employeeSkills;
}