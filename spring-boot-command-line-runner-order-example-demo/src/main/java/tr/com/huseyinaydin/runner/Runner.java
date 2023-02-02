package tr.com.huseyinaydin.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Component
public class Runner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("The Runner start to initialize ...");
    }
}