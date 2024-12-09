// NotificationController.java
package tr.com.huseyinaydin.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Controller
public class NotificationController {

    @MessageMapping("/sendMessage") // JavaScript kısmının hedefi ile eşleşen nokta.
    @SendTo("/topic/notifications") // İlgili adrese gönderir.
    public String sendMessage(String message) {
        System.out.println("Gelen mesaj: " + message);
        return message; // mesajı return ederek yayınla
    }
}