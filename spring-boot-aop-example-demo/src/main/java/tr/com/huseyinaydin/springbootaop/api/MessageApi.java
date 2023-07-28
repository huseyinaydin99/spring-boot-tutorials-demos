package tr.com.huseyinaydin.springbootaop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.com.huseyinaydin.springbootaop.service.EmailService;
import tr.com.huseyinaydin.springbootaop.service.MessageService;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@RestController
@RequestMapping("/message")
public class MessageApi {

    @Autowired
    private MessageService messageService;

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<String> ornekMethod(@RequestBody String message){
    	emailService.mesajVer(message);
        return ResponseEntity.ok(messageService.mesajVer(message));
    }
}