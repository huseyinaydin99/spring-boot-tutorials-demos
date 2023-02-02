package tr.com.huseyinaydin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import tr.com.huseyinaydin.service.MessageProcessor;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@SpringBootApplication
@ImportResource({"classpath*:applicationContext.xml"})
public class MainApplication {

	private static Logger LOG = LoggerFactory.getLogger(MainApplication.class);

	public static void main(String[] args) {
		LOG.info("Uygulama başlıyor. Bismillahirrahmanirrahim.");
		ApplicationContext applicationContext = SpringApplication.run(MainApplication.class, args);
		
		MessageProcessor messageProcessor = applicationContext.getBean(MessageProcessor.class);
		messageProcessor.processMsg("Message sending ");
		LOG.info("Uygulama durdu. Süreç ve akış bitti.!");
	}

}