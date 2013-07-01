package locationshare.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locationshare.common.util.StringUtil;

/**
 * Servlet Filter implementation class RequestFilter
 */
public class RequestFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {

			HttpServletRequest req = (HttpServletRequest) request;
			String requestUrl = req.getRequestURI();

			String userAgentString = req.getHeader("user-agent");
			if (!StringUtil.isStaticPage(requestUrl)
					&& (req.getMethod() == "GET" || !StringUtil
							.isMobileClient(userAgentString))) {
				HttpServletResponse res = (HttpServletResponse) response;
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}

		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
