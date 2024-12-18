package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.entity.Employee;
import tr.com.huseyinaydin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/count")
    public ResponseEntity<String> getTotalEmployeeCount() {
        return ResponseEntity.ok("Toplam çalışan sayısı: " + service.fetchEmployees());
    }

    @GetMapping("/salary/{amount}")
    public ResponseEntity<List<Employee>> getEmployeesBySalaryRange(@PathVariable double amount) {
        return ResponseEntity.ok(service.getEmployeesBySalary(amount));
    }
}