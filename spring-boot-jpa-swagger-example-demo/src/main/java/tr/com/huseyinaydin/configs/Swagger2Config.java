package tr.com.huseyinaydin.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Configuration
@EnableSwagger2
public class Swagger2Config {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors
				.basePackage("tr.com.huseyinaydin.controllers"))
				.paths(PathSelectors.regex("/.*"))
				.build()
				.apiInfo(apiEndPointsInfo());
	}

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("Spring Boot REST API")
				.description("Aşırı Yüksek Bilgisayar Teknikeri ++")
				.contact(new Contact("Hüseyin Aydın", "huseyinaydin99@gmail.com", "truncatecreate@gmail.com"))
				.license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
				.version("1.0.0")
				.build();
	}
}