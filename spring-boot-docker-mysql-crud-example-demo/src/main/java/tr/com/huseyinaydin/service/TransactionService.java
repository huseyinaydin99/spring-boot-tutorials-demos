package tr.com.huseyinaydin.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.huseyinaydin.model.Transaction;
import tr.com.huseyinaydin.repository.TransactionRepository;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Transactional
@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	public Transaction saveTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	public Iterable<Transaction> getTransactionHistory() {
		return transactionRepository.findAll();
	}

	public Optional<Transaction> getTransaction(Long id) {
		return transactionRepository.findById(id);
	}

	public void deleteTransaction(Transaction transaction) {
		transactionRepository.delete(transaction);
	}
}