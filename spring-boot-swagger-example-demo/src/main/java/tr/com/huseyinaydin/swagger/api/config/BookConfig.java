package tr.com.huseyinaydin.swagger.api.config;

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
* @category Java, Spring Boot Swagger.
* 
*/

@Configuration
@EnableSwagger2
public class BookConfig {
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Hüseyin AYDIN").apiInfo(apiInfo()).select()
				.paths(regex("/book.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Kitap Servisi")
				.description("Book REST-API için Swagger ile oluşturulan dökümantasyonum.")
				.termsOfServiceUrl("https://www.youtube.com/channel/UCORuRdpN2QTCKnsuEaeK-kQ")
				.license("Hüseyin AYDIN Lisansı")
				.licenseUrl("https://www.youtube.com/channel/UCORuRdpN2QTCKnsuEaeK-kQ").version("5.4").build();
	}
}