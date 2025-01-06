package tr.com.huseyinaydin.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tr.com.huseyinaydin.model.Ticket;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Repository
public class TicketDao {

	@Autowired
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Ticket> getTicketInfo() {
		return em.createNamedStoredProcedureQuery("firstProcedure").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> getTicketInfoByCatagory(String input) {
		return em.createNamedStoredProcedureQuery("secondProcedure").setParameter("tcatagory", input).getResultList();
	}
}