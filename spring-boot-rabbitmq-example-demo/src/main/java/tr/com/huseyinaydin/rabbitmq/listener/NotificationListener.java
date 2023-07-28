package tr.com.huseyinaydin.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import tr.com.huseyinaydin.rabbitmq.model.Notification;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@Service
public class NotificationListener {

    @RabbitListener(queues = "huseyinaydin-queue")
    public void handleMessage(Notification notification) {
        System.out.println("Message received...");
        System.out.println(notification.toString());
    }
}