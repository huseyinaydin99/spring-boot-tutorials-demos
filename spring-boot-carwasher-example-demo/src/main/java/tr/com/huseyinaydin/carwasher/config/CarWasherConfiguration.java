package tr.com.huseyinaydin.carwasher.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import tr.com.huseyinaydin.carwasher.web.TestFilter;
import tr.com.huseyinaydin.carwasher.web.TestServlet;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@Configuration
@ServletComponentScan
public class CarWasherConfiguration {
	
	@Bean
	public ServletRegistrationBean<TestServlet> testServlet(){
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new TestServlet(), "/TestServlet");
		registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return registrationBean;
	}
	
	@Bean
	public FilterRegistrationBean<TestFilter> testFilter(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new TestFilter());
		registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		registrationBean.setUrlPatterns(Arrays.asList("/TestServlet"));
		return registrationBean;
	}
}
