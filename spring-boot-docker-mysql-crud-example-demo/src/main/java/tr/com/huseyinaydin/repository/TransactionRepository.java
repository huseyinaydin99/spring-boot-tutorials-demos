package tr.com.huseyinaydin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tr.com.huseyinaydin.model.Transaction;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}