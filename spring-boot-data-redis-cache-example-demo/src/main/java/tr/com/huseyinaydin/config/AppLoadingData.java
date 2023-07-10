package tr.com.huseyinaydin.config;

import org.springframework.context.annotation.Configuration;

import tr.com.huseyinaydin.model.Employee;
import tr.com.huseyinaydin.repository.EmployeeRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 **/

@Configuration
public class AppLoadingData {

	private final EmployeeRepository employeeRepository;

	public AppLoadingData(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@PostConstruct
	private void loadingData() throws ParseException {

		var dob = this.date("1990-02-11");
		var hiredDate = this.date("2012-02-11");
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(1, "102299", "Hüseyin", "****", "Bal", "IT", "0892547**", hiredDate, "Backend", "6",
				"M", dob, new BigDecimal(250_000), 123_000.0, 10_000.0));
		employees.add(new Employee(2, "102298", "Salih", "****", "Dal", "IT", "0892547**", hiredDate, "Backend", "6",
				"F", dob, new BigDecimal(230_000), 123_00.0, 1_000.0));

		employeeRepository.saveAll(employees);
	}

	protected Date date(String date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.parse(date);
	}
}
