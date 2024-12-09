// WebSocketConfig.java
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
//Spring uygulamasında WebSocket mesajlaşma altyapısını etkinleştirerek istemcilerle sunucu arasında mesaj yönlendirme ve broker yapılandırmasını sağlar.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");  // mesajları yayınlamak için gereken ön ek
        config.setApplicationDestinationPrefixes("/app");  // İstemciden sunucuya iletişim için gereken önek
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")  // websocket end-point'i
                .setAllowedOrigins("http://localhost:63342")  // tüm frontend'ler için erişim (localhost:63342)
                .withSockJS();  // WebSocket bağlantısı desteklenmeyen tarayıcılarda SockJS protokolü üzerinden geriye dönük uyumluluk sağlayarak iletişimin devam etmesini mümkün kılar.
    }
}