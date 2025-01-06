package tr.com.huseyinaydin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tr.com.huseyinaydin.dao.TicketDao;
import tr.com.huseyinaydin.model.Ticket;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@RestController
public class TicketController {

	@Autowired
	private TicketDao dao;

	@GetMapping("/findTicket")
	public List<Ticket> findTickets() {
		return dao.getTicketInfo();
	}

	@GetMapping("/findTicketByCatagory/{catagory}")
	public List<Ticket> findTicketsByCataogory(@PathVariable String catagory) {
		return dao.getTicketInfoByCatagory(catagory);
	}
}