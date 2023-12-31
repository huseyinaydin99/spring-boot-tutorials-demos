package tr.com.huseyinaydin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot Virtual Thread vs Platform Thread.
 *
 */

@SpringBootApplication
@Slf4j
public class VirtualThreadVSPlatformThreadTestApp {

    public static void main(String[] args) {
        SpringApplication.run(VirtualThreadVSPlatformThreadTestApp.class, args);
    }


//    @Bean
//    public TomcatProtocolHandlerCustomizer<?> tomcatProtocolHandlerCustomizer() {
//        return protocolHandler -> {
//            protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
//        };
//    }
}