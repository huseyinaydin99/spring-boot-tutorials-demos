package tr.com.huseyinaydin.spring.jdbi.api.repository;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import tr.com.huseyinaydin.spring.jdbi.api.model.Order;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@RegisterMapper(value = OrderMapper.class)
public interface OrderSQL {

	@SqlQuery("Select * from Order_TB")
	public List<Order> getOrders();

	@SqlUpdate("insert into Order_TB (order_name,price,quantity,purchaseDate)"
			+ "value(:order_name,:price,:quantity,:purchaseDate)")
	@GetGeneratedKeys
	public Integer addProduct(@BindBean Order order);

	@SqlQuery("SELECT * FROM Order_TB WHERE price = :price")
	abstract List<Order> getOrderByPrice(@Bind("price") int price);

}
