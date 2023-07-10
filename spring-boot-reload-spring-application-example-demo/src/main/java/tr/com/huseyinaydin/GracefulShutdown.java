package tr.com.huseyinaydin;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 **/

@Slf4j
public class GracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

	private volatile Connector connector;

	private static final int TIMEOUT = 30;

	@Override
	public void customize(Connector connector) {
		this.connector = connector;
	}

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		this.connector.pause();
		Executor executor = this.connector.getProtocolHandler().getExecutor();
		if (executor instanceof ThreadPoolExecutor) {
			try {
				ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
				threadPoolExecutor.shutdown();
				if (!threadPoolExecutor.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
					log.warn("Tomcat thread pool did not shut down gracefully within "
							+ "30 seconds. Proceeding with forceful shutdown");
				}
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}
}