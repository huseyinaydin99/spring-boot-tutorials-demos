package tr.com.huseyinaydin.spring.camel.api.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tr.com.huseyinaydin.spring.camel.api.dto.Order;
import tr.com.huseyinaydin.spring.camel.api.service.OrderService;

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 * 
 */

@Component
public class OrderProcessor implements Processor{
	
	@Autowired
	private OrderService service;

	@Override
	public void process(Exchange exchange) throws Exception {
		service.addOrder(exchange.getIn().getBody(Order.class));
	}
}