package tr.com.huseyinaydin.service.impl;

import lombok.extern.slf4j.Slf4j;
import tr.com.huseyinaydin.dto.EmployeeDto;
import tr.com.huseyinaydin.exception.EmployeeExceptionNotFound;
import tr.com.huseyinaydin.model.Employee;
import tr.com.huseyinaydin.repository.EmployeeRepository;
import tr.com.huseyinaydin.service.EmployeeService;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 **/

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final ModelMapper modelMapper;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
		this.employeeRepository = employeeRepository;
		this.modelMapper = modelMapper;
	}

	@Cacheable(value = "EmployeeDto", key = "#empNo")
	@Override
	public EmployeeDto findByEmployeeNo(String empNo) {
		log.info("Fetch employee from db by emp-no {}", empNo);
		Optional<Employee> employee = employeeRepository.findFirstByEmployeeNo(empNo);
		return employee.map(value -> modelMapper.map(value, EmployeeDto.class))
				.orElseThrow(() -> new EmployeeExceptionNotFound("Employee not found!"));
	}

	@Cacheable(value = "EmployeeDtoList")
	@Override
	public List<EmployeeDto> getAll() {
		log.info("Fetch all employees from db");
		List<Employee> employees = employeeRepository.findAll();
		if (!employees.isEmpty()) {
			return employees.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class))
					.collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

	@Caching(put = { @CachePut(value = "EmployeeDto", key = "#empNo") }, evict = {
			@CacheEvict(value = "EmployeeDtoList", allEntries = true) })
	@Override
	public EmployeeDto update(EmployeeDto employeeDto, String empNo) {
		Optional<Employee> employee = employeeRepository.findFirstByEmployeeNo(empNo);
		if (employee.isPresent()) {
			employee.get().setSex(employeeDto.getSex());
			employee.get().setEdLevel(employeeDto.getEdLevel());
			Employee tempEmp = employeeRepository.save(employee.get());
			return modelMapper.map(tempEmp, EmployeeDto.class);
		}
		log.error("No employee no {} found", empNo);
		return null;
	}

	@Caching(evict = { @CacheEvict(value = "EmployeeDtoList", allEntries = true),
			@CacheEvict(value = "EmployeeDto", key = "#empNo") })
	@Override
	public void delete(String empNo) {
		Optional<Employee> employee = employeeRepository.findFirstByEmployeeNo(empNo);
		employee.ifPresent(employeeRepository::delete);
	}
}
