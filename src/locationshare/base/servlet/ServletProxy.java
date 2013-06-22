package locationshare.base.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ServletProxy extends HttpServlet{

	/** */
	private static final long serialVersionUID = -4481366626824517285L;
	private BaseServlet proxy;

	public void init() throws ServletException {
	}

	public void doGet( HttpServletRequest req, HttpServletResponse res ) throws IOException {
		
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		this.proxy = (BaseServlet) wac.getBean(getInitParameter("targetBean"));
		proxy.setLogger(LogFactory.getLog(proxy.getClass()));
		proxy.doGet(req, res);
		
	}
	
	public void doPost( HttpServletRequest req, HttpServletResponse res ) throws IOException {
		
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		this.proxy = (BaseServlet) wac.getBean(getInitParameter("targetBean"));
		proxy.setLogger(LogFactory.getLog(proxy.getClass()));
		proxy.doPost(req, res);
	}
}
