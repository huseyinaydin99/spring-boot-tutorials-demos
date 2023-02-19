package tr.com.huseyinaydin.spring.kotlin.crud.api.controller

import tr.com.huseyinaydin.spring.kotlin.crud.api.model.Company
import tr.com.huseyinaydin.spring.kotlin.crud.api.repository.CompanyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.DeleteMapping

/**
 *
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
 *
 **/

@RestController
class CompanyController {

	@Autowired
	lateinit var companyRepository: CompanyRepository;

	@PostMapping("/saveCompany")
	fun save(@RequestBody company: Company): String {
		companyRepository.save(company);
		return "Comapny details saved..";
	}

	@GetMapping("/getAll")
	fun getAllCompany(): List<Company> {
		return companyRepository.findAll();
	}

	@GetMapping("/getCompnay/{name}")
	fun getCompnay(@PathVariable name: String): Company {
		return companyRepository.findByName(name);
	}

	@DeleteMapping("/delete/{id}")
	fun deleteCompnay(@PathVariable id: Int): String {
		companyRepository.delete(id);
		return "record deleted...";
	}

}