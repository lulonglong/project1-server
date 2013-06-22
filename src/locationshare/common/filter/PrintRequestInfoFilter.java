package locationshare.common.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.TreeMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Descriptions
 * 
 * @version 2013-6-20
 * @author lulonglong
 * @since JDK1.6
 * 
 */
public class PrintRequestInfoFilter implements Filter {

	private static Log logger = LogFactory.getLog(PrintRequestInfoFilter.class);

	public void init(FilterConfig fc) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		final String FUNCTION_NAME = "doFilter() ";
		logger.info(FUNCTION_NAME + "-----【Request信息开始】 -----");

		if (request instanceof HttpServletRequest) {

			HttpServletRequest req = (HttpServletRequest) request;

			logger.info(req.getRequestURI());

			/************* Request Head信息 *************/
			Enumeration<String> headerKeys = req.getHeaderNames();

			TreeMap<String, String> headMap = new TreeMap<String, String>();
			while (headerKeys.hasMoreElements()) {

				String key = headerKeys.nextElement();
				headMap.put(key, req.getHeader(key));
			}

			logger.info(FUNCTION_NAME + headMap);

			/************* Parameter信息 *************/
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);

			if (!isMultipart) {

				Enumeration<String> paramKeys = req.getParameterNames();

				TreeMap<String, String> reqMap = new TreeMap<String, String>();

				while (paramKeys.hasMoreElements()) {

					String key = paramKeys.nextElement();
					reqMap.put(key, req.getParameter(key));
				}

				logger.info(FUNCTION_NAME + reqMap);
			} else {
				logger.info(FUNCTION_NAME + "is multipart");
			}
		}

		logger.info(FUNCTION_NAME + "----- 【Request信息结束】 -----" + "\r\n");
		chain.doFilter(request, response);
	}

	public void destroy() {

	}

}