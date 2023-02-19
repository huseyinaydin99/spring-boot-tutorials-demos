package tr.com.huseyinaydin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.com.huseyinaydin.model.Address;
import tr.com.huseyinaydin.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@RestController
@RequestMapping("/api/v1")
public class AddressController {

	@Autowired
	AddressRepository addressRepository;

	// http://localhost:8082/api/v1/address
	@GetMapping("/address")
	public List<Address> getAddresssAll() {
		return addressRepository.findAll();
	}

	// http://localhost:8082/api/v1/address/1
	@GetMapping("/address/{id}")
	public ResponseEntity<Optional<Address>> getAddressFindById(@PathVariable("id") Long id) {
		Optional<Address> address = addressRepository.findById(id);
		return ResponseEntity.ok().body(address);
	}
}