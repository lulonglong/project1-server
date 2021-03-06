package locationshare.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locationshare.action.LogInAction;
import locationshare.base.servlet.BaseServlet;

/**
 * Servlet implementation class LocationServlet
 */

public class ValidateRegisterServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7392733831361287621L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String usernameString = req.getParameter("username");
		return logInAction.validateRegister(usernameString);
	}

	private LogInAction logInAction;

	public void setLogInAction(LogInAction logInAction) {
		this.logInAction = logInAction;
	}
}
