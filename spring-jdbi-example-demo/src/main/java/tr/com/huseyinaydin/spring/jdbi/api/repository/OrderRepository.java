package tr.com.huseyinaydin.spring.jdbi.api.repository;

import java.sql.Connection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import tr.com.huseyinaydin.spring.jdbi.api.model.Order;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Repository
public class OrderRepository {

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	Connection connection = null;
	Handle handle = null;
	OrderSQL orderSQL = null;

	@PostConstruct
	public void init() {
		connection = DataSourceUtils.getConnection(dataSource);
		handle = DBI.open(connection);
		orderSQL = handle.attach(OrderSQL.class);
	}

	public List<Order> findAllOrders() {
		return orderSQL.getOrders();
	}

	public Integer addOrder(Order order) {
		return orderSQL.addProduct(order);
	}

	public List<Order> getOrderByPrice(int price) {
		return orderSQL.getOrderByPrice(price);
	}

}
