package tr.com.huseyinaydin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.javalite.activejdbc.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Component
public class ApplicationFilter implements Filter {

	Logger LOGGER = LoggerFactory.getLogger(ApplicationFilter.class);

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("Kaynak açıldı....");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		long before = System.currentTimeMillis();
		try {
			Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/huseyin_aydin_db", "root", "toor");
			Base.openTransaction();
			chain.doFilter(req, resp);
			Base.commitTransaction();
		} catch (IOException | ServletException e) {
			Base.rollbackTransaction();
			throw e;
		} finally {
			Base.close();
		}
		LOGGER.info("İşlem süresi: {} milisaniye", System.currentTimeMillis() - before);
	}

	@Override
	public void destroy() {
		System.out.println("Kaynak kapatıldı....");
	}
}