package tr.com.huseyinaydin.spring.camel.api.resource;

import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import tr.com.huseyinaydin.spring.camel.api.dto.Order;
import tr.com.huseyinaydin.spring.camel.api.processor.OrderProcessor;
import tr.com.huseyinaydin.spring.camel.api.service.OrderService;

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 * 
 */

@Component
public class ApplicationResource extends RouteBuilder {

	@Autowired
	private OrderService service;

	@BeanInject
	private OrderProcessor processor;

	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").port(9090).host("localhost").bindingMode(RestBindingMode.json);

		rest().get("/hello-world").produces(MediaType.APPLICATION_JSON_VALUE).route()
				.setBody(constant("Welcome")).endRest();

		rest().get("/getOrders").produces(MediaType.APPLICATION_JSON_VALUE).route().setBody(() -> service.getOrders())
				.endRest();

		rest().post("/addOrder").consumes(MediaType.APPLICATION_JSON_VALUE).type(Order.class).outType(Order.class)
				.route().process(processor).endRest();
	}
}