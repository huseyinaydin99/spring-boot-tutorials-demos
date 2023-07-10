package tr.com.huseyinaydin;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@SpringBootApplication
public class ReloadSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReloadSpringApplication.class, args);
    }

    @Bean("gracefulShutdown")
    public GracefulShutdown gracefulShutdown() {
        return new GracefulShutdown();
    }
    
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory(@Qualifier("gracefulShutdown") GracefulShutdown gracefulShutdown) {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(gracefulShutdown);
        return factory;
    }
}
