package tr.com.huseyinaydin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tr.com.huseyinaydin.service.FakeAPIService;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@SpringBootApplication
public class SpringBootRedisStackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisStackApplication.class, args);
    }

    @Autowired
    FakeAPIService fakeAPIService;

    @Bean
    void test() {
        fakeAPIService.callingToProductAPI();
        fakeAPIService.callingToUsersAPI();
        fakeAPIService.callingToUserAPI();
    }
}
