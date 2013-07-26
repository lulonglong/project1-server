package locationshare.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locationshare.action.LogInAction;
import locationshare.base.servlet.BaseServlet;
import locationshare.common.util.StringUtil;

public class ExceptionRecordServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5506365599134126358L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		
		String userAgent=req.getHeader("User-Agent");
		String exception = req.getParameter("exception") ;
		String phoneos = StringUtil.getClientOS(userAgent);
		String appversion =StringUtil.getClientAppVersion(userAgent);
		return logInAction.recordException(exception, phoneos,appversion);
	}
	private LogInAction logInAction;

	public void setLogInAction(LogInAction logInAction) {
		this.logInAction = logInAction;
	}
}
