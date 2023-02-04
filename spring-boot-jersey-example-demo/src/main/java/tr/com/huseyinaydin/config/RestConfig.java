package tr.com.huseyinaydin.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import tr.com.huseyinaydin.resource.EmployeeResource;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Component
public class RestConfig extends ResourceConfig{

	public RestConfig(Class<?>... classes) {
		register(EmployeeResource.class);
	}
}
