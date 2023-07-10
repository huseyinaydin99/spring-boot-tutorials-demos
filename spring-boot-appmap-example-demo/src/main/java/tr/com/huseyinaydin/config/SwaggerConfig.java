package tr.com.huseyinaydin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Huseyin_Aydin").apiInfo(apiInfo()).select()
                .paths(regex("/api/employee.*")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Employee Service")
                .description("Sample Documentation Generated Using SWAGGER2 for our Book Rest API")
                .version("2.0").build();
    }

}
