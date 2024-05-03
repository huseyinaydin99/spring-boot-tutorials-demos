package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.dto.Customer;
import tr.com.huseyinaydin.dto.User;
import tr.com.huseyinaydin.publisher.KafkaMessagePublisher;
import tr.com.huseyinaydin.util.CsvReaderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    private KafkaMessagePublisher publisher;


    @PostMapping("/publishNew")
    public ResponseEntity<?> publishEvent(@RequestBody User user) {
        try {
            List<User> users = CsvReaderUtils.readDataFromCsv();
            users.forEach(usr -> publisher.sendEvents(usr));
            return ResponseEntity.ok("Mesaj başarı ile gönderildi.");
        } catch (Exception exception) {
            return ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR) //statu kodu 500
                    .build();
        }
    }

    @PostMapping("/publishNewCustomer")
    public ResponseEntity<?> publishEvent(@RequestBody Customer customer) {
        try {
            List<Customer> customers = Stream.of(new Customer(1, "nma","rte","ghi"), new Customer(2, "Veli","abcabc","cbacba")).collect(Collectors.toList());
            customers.forEach(item -> publisher.sendEvents(item));
            return ResponseEntity.ok("Mesaj başarı ile gönderildi.");
        } catch (Exception exception) {
            return ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR) //statu kodu 500
                    .build();
        }
    }
}