package locationshare.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locationshare.action.LogInAction;
import locationshare.base.servlet.BaseServlet;

public class ExceptionRecordServlet extends BaseServlet {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		// TODO Auto-generated method stub
		String userid = req.getParameter("userid");
		String exception = req.getParameter("exception") ;
		String phoneos = req.getParameter("phoneos") ;
		String appversion = req.getParameter("appversion") ;
		String date = req.getParameter("date") ;
//		TbException(int userid, Date exceptionTime, String loginOs,
//				String exceptionDsp, String appVersion		
//		String d
		return logInAction.recordException(userid, exception, phoneos,appversion,date);
	}
	private LogInAction logInAction;

	public void setLogInAction(LogInAction logInAction) {
		this.logInAction = logInAction;
	}
}
