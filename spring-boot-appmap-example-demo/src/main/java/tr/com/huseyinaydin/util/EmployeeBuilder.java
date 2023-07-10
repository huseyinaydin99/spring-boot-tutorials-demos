package tr.com.huseyinaydin.util;

import java.util.Arrays;
import java.util.List;

import tr.com.huseyinaydin.dto.EmployeeDTO;
import tr.com.huseyinaydin.entity.Employee;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

public class EmployeeBuilder {

    public static List<EmployeeDTO> getListDTO() {
        return Arrays.asList(new EmployeeDTO(1,"Hüseyin","DEV",50000),
                new EmployeeDTO(2,"Recep","QA",80000));
    }

    public static List<Employee> getListEntities() {
        return Arrays.asList(new Employee(1,"Samet","DEV",50000),
                new Employee(2,"Hasan","QA",80000));
    }

    public static EmployeeDTO getDTO() {
        return new EmployeeDTO(1,"Ceyda","DEV",50000);
    }

    public static Employee getEntity() {
        return new Employee(1,"Gizem","DEV",50000);
    }
}
