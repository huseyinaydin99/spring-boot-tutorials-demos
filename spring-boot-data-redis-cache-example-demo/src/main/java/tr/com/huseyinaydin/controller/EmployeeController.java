package tr.com.huseyinaydin.controller;

import lombok.extern.slf4j.Slf4j;
import tr.com.huseyinaydin.dto.EmployeeDto;
import tr.com.huseyinaydin.exception.EmployeeExceptionNotFound;
import tr.com.huseyinaydin.service.EmployeeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 **/

@Slf4j
@RestController
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("emp")
	public ResponseEntity<Object> findEmployeeByNo(@RequestParam String empNo) {
		log.info("GET-- employee by emp-no {}", empNo);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeService.findByEmployeeNo(empNo));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new EmployeeExceptionNotFound("Unexpected error!"));
		}
	}

	@GetMapping("emp/all")
	public ResponseEntity<Object> getAll() {
		log.info("GET-- all employees");
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new EmployeeExceptionNotFound("Unexpected error!"));
		}
	}

	@PostMapping("emp")
	public ResponseEntity<Object> updateEmployee(@RequestBody EmployeeDto employeeDto) {
		log.info("UPDATE-- employee with req: {}", employeeDto);
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(employeeService.update(employeeDto, employeeDto.getEmployeeNo()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new EmployeeExceptionNotFound("Unexpected error!"));
		}
	}

	@DeleteMapping("emp/{empNo}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable("empNo") String empNo) {
		log.info("DELETE-- employee no {}", empNo);
		try {
			employeeService.delete(empNo);
			return ResponseEntity.status(HttpStatus.OK).body("");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new EmployeeExceptionNotFound("Unexpected error!"));
		}
	}

}
