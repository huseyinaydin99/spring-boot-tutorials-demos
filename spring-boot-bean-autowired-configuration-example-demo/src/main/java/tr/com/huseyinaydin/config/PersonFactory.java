package tr.com.huseyinaydin.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tr.com.huseyinaydin.model.Manager;
import tr.com.huseyinaydin.model.Person;
import tr.com.huseyinaydin.model.Student;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@Configuration
public class PersonFactory {

    @Bean
    @Qualifier("student")
    public Person createStudent() {
        return new Student();
    }
    
    @Bean
    @Qualifier("manager")
    public Person createManager() {
        return new Manager();
    }    
}