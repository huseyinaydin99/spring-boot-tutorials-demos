package tr.com.huseyinaydin.spring.rest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.com.huseyinaydin.spring.rest.api.model.BookRequest;
import tr.com.huseyinaydin.spring.rest.api.repository.BookMyShowRepository;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@SpringBootApplication
@RestController
@RequestMapping("BookMyShow/Service")
public class BookMyShowApplication {

	@Autowired
	private BookMyShowRepository repository;

	@PostMapping("/bookingShow")
	public String bookShow(@RequestBody BookRequest bookRequest) {
		BookRequest response = repository.save(bookRequest);
		return "Hi " + response.getUserName() + " your Request for " + response.getShowName() + " on date "
				+ response.getBookingTime() + "Booking successfully..";
	}

	@GetMapping("/getAllBooking")
	public List<BookRequest> getAllBooking() {
		return repository.findAll();
	}

	@GetMapping("/getBooking/{bookingId}")
	public BookRequest getBooking(@PathVariable int bookingId) {
		return repository.findById(bookingId).get();
	}

	@DeleteMapping("/cancelBooking/{bookingId}")
	public String cancelBooking(@PathVariable int bookingId) {
		repository.deleteById(bookingId);
		return "Booking cancelled with bookingId : " + bookingId;
	}

	@PutMapping("/updateBooking/{bookingId}")
	public BookRequest updateBooking(@RequestBody BookRequest updateBookRequest, @PathVariable int bookingId) {
		BookRequest dbResponse = repository.findById(bookingId).get();
		dbResponse.setBookingTime(updateBookRequest.getBookingTime());
		dbResponse.setPrice(updateBookRequest.getPrice());
		dbResponse.setShowName(updateBookRequest.getShowName());
		dbResponse.setUserCount(updateBookRequest.getUserCount());
		repository.saveAndFlush(dbResponse);
		return dbResponse;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class, args);
	}
}