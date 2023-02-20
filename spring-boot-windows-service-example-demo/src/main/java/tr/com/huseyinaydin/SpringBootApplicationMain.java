package tr.com.huseyinaydin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 * 
 */

@SpringBootApplication
@RestController
@EnableScheduling
public class SpringBootApplicationMain {

    @GetMapping("/status")
    public String getStatus() {
        return "Application is running...!!";
    }

    @Scheduled(fixedDelay = 5000)
    public void print(){
        System.out.println("Current Time : "+new Date());
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationMain.class, args);
    }
}