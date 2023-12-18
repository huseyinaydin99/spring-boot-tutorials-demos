package tr.com.huseyinaydin.jsondocs.api.controller;

import java.util.List;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tr.com.huseyinaydin.jsondocs.api.model.Employee;
import tr.com.huseyinaydin.jsondocs.api.service.EmployeeService;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot JSONDocs.
* 
*/

@RestController
@Api(name = "Çalışanlar Yönetim Sistemi", description = "Çalışanların bilgisini veren API'dir", group = "Yonetim", visibility = ApiVisibility.PUBLIC, stage = ApiStage.BETA)
public class EmployeeController {
	@Autowired
	private EmployeeService service;

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	@ApiMethod(description = "Yeni çalışan ekleme metodu.")
	public String save(@RequestBody Employee employee) {
		return service.saveEmployee(employee);
	}

	@RequestMapping(value = "/getEmployee/{id}", method = RequestMethod.GET)
	@ApiMethod(description = "Id değerine göre çalışanı okuyan metot.", path = { "id" })
	public Employee getEmployee(
			@PathVariable @ApiPathParam(description = "Çalışanı okumak için Id değeri parametresidir.", name = "id") int id) {
		return service.getEmployee(id);
	}

	@RequestMapping(value = "/deleteEmployee/{id}", method = RequestMethod.DELETE)
	@ApiMethod(description = "Id değerine göre çalışanı silen metot.", path = { "id" })
	public List<Employee> deleteEmployee(
			@PathVariable @ApiPathParam(description = "Çalışanı silmek için Id değeri parametresidir.", name = "id") int id) {
		return service.deleteEmployee(id);
	}
}