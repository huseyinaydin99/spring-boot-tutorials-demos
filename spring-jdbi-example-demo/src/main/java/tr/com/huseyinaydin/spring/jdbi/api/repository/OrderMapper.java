package tr.com.huseyinaydin.spring.jdbi.api.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import tr.com.huseyinaydin.spring.jdbi.api.model.Order;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public class OrderMapper implements ResultSetMapper<Order> {

	@Override
	public Order map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Order(r.getInt("id"), r.getString("order_name"), r.getInt("price"), r.getInt("quantity"),
				r.getDate("purchaseDate"));
	}
}
