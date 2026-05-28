package tr.com.huseyinaydin;

import org.springframework.security.access.prepost.PreAuthorize;
import tr.com.huseyinaydin.entity.Employee;
import tr.com.huseyinaydin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/employees")
public class SpringBootKeycloakExampleApplication {

    @Autowired
    private EmployeeService service;

    //Bu metoda yalnızca “user/normal sıradan kullanıcı” rolüne sahip kullanıcılar erişebilir.
    @GetMapping("/{employeeId}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Employee> getEmployee(@PathVariable int employeeId) {
        return ResponseEntity.ok(service.getEmployee(employeeId));
    }

    //Bu metoda yalnızca “admin/yönetici” rolüne sahip kullanıcılar erişebilir.
    @GetMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<Employee>> findALlEmployees() {
        return ResponseEntity.ok(service.getAllEmployees());
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKeycloakExampleApplication.class, args);
    }

}