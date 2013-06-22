package locationshare.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locationshare.action.XXXAction;
import locationshare.base.servlet.BaseServlet;

/**
 * Servlet implementation class UpdateBatchLocationServlet
 */
public class SendToUsersServlet extends BaseServlet {
	/** */
	private static final long serialVersionUID = 7392733831361287601L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		// TODO Auto-generated method stub
		return "SendToUsersServlet-OK";
	}

	private XXXAction xXXAction;

	public void setXXXAction(XXXAction xXXAction) {
		this.xXXAction = xXXAction;
	}

}
