package tr.com.huseyinaydin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tr.com.huseyinaydin.service.EmailService;
import tr.com.huseyinaydin.service.FileService;
import tr.com.huseyinaydin.service.SmsService;
import tr.com.huseyinaydin.service.MessageProcessor;
import tr.com.huseyinaydin.service.MessageProcessorImpl;
import tr.com.huseyinaydin.service.SelectService;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Configuration
public class ApplicationContextJavaConfig {

	@Bean(name = "emailService")
	public SelectService emailService() {
		return new EmailService();
	}

	@Bean(name = "smsService")
	public SelectService smsService() {
		return new SmsService();
	}

	@Bean(name = "fileService")
	public SelectService fileService() {
		return new FileService();
	}

	@Bean
	public MessageProcessor messageProcessor() {
		return new MessageProcessorImpl(smsService());
	}
}