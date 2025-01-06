package tr.com.huseyinaydin.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Entity
@Table
@Getter
@Setter
@NamedStoredProcedureQueries({ @NamedStoredProcedureQuery(name = "firstProcedure", procedureName = "getTickets"),
				@NamedStoredProcedureQuery(name = "secondProcedure", procedureName = "getTicketsByCatagory", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "tcatagory", type = String.class)
				})
})
public class Ticket {

	@Id
	private int id;

	private int amount;
	private String catagory;
}