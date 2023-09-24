package tr.com.huseyinaydin.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Data
@AllArgsConstructor
@Component
@NoArgsConstructor
public class DataSourceConfig {

    private String username;
    private String password;


}
