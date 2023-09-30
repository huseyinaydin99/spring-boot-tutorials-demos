package tr.com.huseyinaydin.spring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@ConfigurationProperties(prefix = "mail")
@Data
@Component
public class MailProps {
    private String from;
    private String host;
    private String port;
}