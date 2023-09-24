package tr.com.huseyinaydin.di;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Service
public class OrderService {

    private RestClientService restClientService;

    @Autowired
    @Lazy
    public void setRestClientService(RestClientService restClientService) {
        this.restClientService = restClientService;
    }

//    private OrderRepository orderRepository;

//    //    //Setter DI
//    //optional dependency injection
//    //not immutable in nature
//    //circular dependency can't resolve
//    @Autowired
//    public void setOrderRepository(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }
//
//    //Constructor DI
//    //Mandatory dependency injection
//    //immutable in nature
//    //circular dependency can't resolve
//    @Autowired
//    public OrderService(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }
//
//
//    public void test() {
//        orderRepository.saveOrder();
//    }
}
