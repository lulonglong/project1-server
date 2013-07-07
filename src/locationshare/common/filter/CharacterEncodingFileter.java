
package locationshare.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Descriptions
 * 
 * @version 2013-6-20
 * @author lulonglong
 * @since JDK1.6
 * 
 */
public class CharacterEncodingFileter implements Filter {

	private String charset;

	public void init(FilterConfig config) throws ServletException {
		/*
		 * <param-name>charset</param-name> in web.xml 
		 */
		charset = config.getInitParameter("charset");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		request.setCharacterEncoding(charset);
		chain.doFilter(request, response);
	}

	public void destroy() {

	}

}