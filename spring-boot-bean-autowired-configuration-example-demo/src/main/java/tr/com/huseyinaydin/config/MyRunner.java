package tr.com.huseyinaydin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import tr.com.huseyinaydin.model.Person;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@Component
public class MyRunner implements CommandLineRunner {
    
    @Autowired
    @Qualifier("student")
    Person p1;
    
    @Autowired
    @Qualifier("manager")
    Person p2;    
    
    @Override
    public void run(String... args) throws Exception {

        System.out.println(p1.info());        
        System.out.println(p2.info());
    }
}