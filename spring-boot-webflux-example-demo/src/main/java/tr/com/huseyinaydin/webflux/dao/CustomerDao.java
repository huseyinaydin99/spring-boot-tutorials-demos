package tr.com.huseyinaydin.webflux.dao;

import tr.com.huseyinaydin.webflux.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Component
public class CustomerDao {
    private static void sleepExecution(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomers()  {
        return IntStream.rangeClosed(1, 10)
                .peek(CustomerDao::sleepExecution)
                .peek(i -> System.out.println("İşlem sayısı : " + i))
                .mapToObj(i -> new Customer(i, "Müşteri: " + i))
                .collect(Collectors.toList());
    }


    public Flux<Customer> getCustomersStream()  {
        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Akıştaki işlem sayısı: " + i))
                .map(i -> new Customer(i, "Müşteri: " + i));
    }


    public Flux<Customer> getCustomerList()  {
        return Flux.range(1,50)
                .doOnNext(i -> System.out.println("Akış sayısı: " + i))
                .map(i -> new Customer(i, "Müşteri: " + i));
    }
}