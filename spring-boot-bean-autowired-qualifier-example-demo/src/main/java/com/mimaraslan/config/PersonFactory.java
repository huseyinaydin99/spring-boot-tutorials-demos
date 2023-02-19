package com.mimaraslan.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mimaraslan.model.Manager;
import com.mimaraslan.model.Person;
import com.mimaraslan.model.Student;

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