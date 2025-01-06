package tr.com.huseyinaydin.winservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@SpringBootApplication
@RestController
@EnableScheduling
public class JavatechieApplication {

    @GetMapping("/status")
    public String getStatus() {
        return "Uygulama ayağa kalktı kardeşim.";
    }

    //Her 5 saniyede 1 çalışır.
    @Scheduled(fixedDelay = 5000)
    public void print(){
        System.out.println("Sistem tarih ve saati: " + new Date());
    }

    public static void main(String[] args) {
        SpringApplication.run(JavatechieApplication.class, args);
    }
}