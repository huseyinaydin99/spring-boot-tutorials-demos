package tr.com.huseyinaydin.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import tr.com.huseyinaydin.rabbitmq.model.Notification;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.UUID;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@Service
public class NotificationProducer {

    @Value("${sr.rabbit.routing.name}")
    private String routingName;

    @Value("${sr.rabbit.exchange.name}")
    private String exchangeName;
    
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){

        Notification notification = new Notification();
        notification.setNotificationId(UUID.randomUUID().toString());
        notification.setCreatedAt(new Date());
        notification.setMessage("Spring Examples RabbitMq");
        notification.setSeen(Boolean.FALSE);

        sendToQueue(notification);
    }

    public void sendToQueue(Notification notification) {
        System.out.println("Notification Sent ID: " +notification.getNotificationId());
        rabbitTemplate.convertAndSend(exchangeName, routingName, notification);
    }
}