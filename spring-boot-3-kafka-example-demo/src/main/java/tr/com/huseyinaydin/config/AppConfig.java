package tr.com.huseyinaydin.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@Data
@Configuration
@ConfigurationProperties(prefix = "app.spring")
public class AppConfig {

    private String bootstrapServers;
    private String topicName;
    private String consumerGroupId;

    @PostConstruct
    public void init(){
        System.out.println("bootstrapServers: " + bootstrapServers
            + "\r\n consumerGroupId: " + consumerGroupId);
    }

}