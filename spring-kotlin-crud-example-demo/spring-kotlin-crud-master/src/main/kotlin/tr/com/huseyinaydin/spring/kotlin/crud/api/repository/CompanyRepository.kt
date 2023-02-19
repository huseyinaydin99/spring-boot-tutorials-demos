package tr.com.huseyinaydin.spring.kotlin.crud.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import tr.com.huseyinaydin.spring.kotlin.crud.api.model.Company

/**
 *
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
 *
 **/

interface CompanyRepository : JpaRepository<Company, Int> {
	fun findByName(name :String): Company
}