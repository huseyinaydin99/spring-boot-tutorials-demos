package tr.com.huseyinaydin.carwasher.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@WebFilter("/TestServlet")
public class TestFilter implements Filter {

	public TestFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Hazırlanıyor...");
		System.out.println("Hazırlandı...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		response.getWriter().write(" Response(cevap) öncesi ");
		chain.doFilter(request, response);
		response.getWriter().write(" Response(cevap) sonrası ");
	}

	@Override
	public void destroy() {
		System.out.println("Kaldırılıyor...");
		System.out.println("Kaldırıldı...");
	}

}
