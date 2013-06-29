package locationshare.base.servlet;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locationshare.common.util.StringUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class BaseServlet extends HttpServlet {
	/** */
	private static final long serialVersionUID = 1L;

	protected Log logger;

	public BaseServlet() {
		logger = LogFactory.getLog(this.getClass());
	}

	public abstract String execute(HttpServletRequest req,
			HttpServletResponse res) throws IOException;

	@Override
	public void init() throws ServletException {
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.sendError(HttpServletResponse.SC_FORBIDDEN);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		String result = null;

		try {

			result = execute(req, res);
			if (!StringUtil.isNotNull(result)) {
				result = "";
			}

		} catch (Exception e) {
			logger.error(StringUtil.getExceptionStack(e));
		} finally {
			response(res, result.getBytes("UTF-8"));
		}

	}

	private void response(HttpServletResponse res, byte[] body)
			throws IOException {

		res.addHeader("Pragma", "no-cache");
		res.addHeader("Cache-Control", "no-cache");
		res.setCharacterEncoding("UTF-8");
		DataOutputStream out = new DataOutputStream(res.getOutputStream());
		out.write(body);
		out.close();
	}

	public Log getLogger() {
		return logger;
	}

	public void setLogger(Log logger) {
		this.logger = logger;
	}
}
