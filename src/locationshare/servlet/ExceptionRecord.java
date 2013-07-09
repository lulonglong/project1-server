package locationshare.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locationshare.action.LogInAction;
import locationshare.base.servlet.BaseServlet;

public class ExceptionRecord extends BaseServlet {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		// TODO Auto-generated method stub
		String userid = req.getParameter("type");
		String exception = req.getParameter("exception") ;
		String phoneos = req.getParameter("phoneos") ;
		return logInAction.recordException(userid, exception, phoneos);
	}
	private LogInAction logInAction;

	public void setLogInAction(LogInAction logInAction) {
		this.logInAction = logInAction;
	}
}
