package tr.com.huseyinaydin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Configuration
@EnableWebSocketMessageBroker
/*
@EnableWebSocketMessageBroker, Spring uygulamalarında WebSocket mesajlaşmasını etkinleştirmek
için kullanılır. Bu dipnot, WebSocket mesajlarının yönlendirilmesini sağlamak için bir
mesaj aracısı (message broker) yapılandırır ve istemciler ile sunucu arasındaki iletişimi kolaylaştırır.
 */
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:63342")
                .withSockJS();
    }
}