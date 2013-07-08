package locationshare.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locationshare.action.LogInAction;
import locationshare.base.servlet.BaseServlet;

/**
 * Servlet implementation class UpdateBatchLocationServlet
 */
public class LogInServlet extends BaseServlet {
	/** */
	private static final long serialVersionUID = 7392733831361287601L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String typeString = req.getParameter("type");
		String usernameString = req.getParameter("username");
		String passwordString = req.getParameter("password");
		return logInAction.logIn(typeString,usernameString,passwordString);
	}

	private LogInAction logInAction;

	public void setLogInAction(LogInAction logInAction) {
		this.logInAction = logInAction;
	}

}
