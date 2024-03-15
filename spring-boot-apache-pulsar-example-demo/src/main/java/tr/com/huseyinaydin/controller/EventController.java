package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.dto.CustomerDto;
import tr.com.huseyinaydin.producer.EventPublisher;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//بسم الله الرحمن الرحيم
/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot, Apache Pulsar.
 *
 */

@RestController
@RequestMapping("/producer")
public class EventController {

    @Autowired
    private EventPublisher publisher;

    @GetMapping("/text/{message}")
    public String sendTextEvent(@PathVariable String message) throws PulsarClientException {
        publisher.publishPlainMessage(message);
        return "Mesaj Apache Pulsar'a yollandı !";
    }

    @PostMapping("/raw")
    public String sendRawEvent(@RequestBody CustomerDto customer) throws PulsarClientException {
        publisher.publishRawMessage(customer);
        return "Özel objemiz Apache Pulsar'a yollandı !";
    }
}