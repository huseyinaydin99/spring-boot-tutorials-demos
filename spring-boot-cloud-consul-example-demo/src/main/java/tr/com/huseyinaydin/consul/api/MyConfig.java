package tr.com.huseyinaydin.consul.api;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;


/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@ConfigurationProperties(prefix="my")
@Data
public class MyConfig {
	private String username;
	private String password;
}