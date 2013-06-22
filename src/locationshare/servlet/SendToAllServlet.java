package locationshare.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locationshare.action.XXXAction;
import locationshare.base.servlet.BaseServlet;

/**
 * Servlet implementation class LocationServlet
 */

public class SendToAllServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7392733831361287621L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		// TODO Auto-generated method stub
		logger.debug("eww我");
		return "SendToAllServlet-OK";
	}

	private XXXAction xXXAction;

	public void setXXXAction(XXXAction xXXAction) {
		this.xXXAction = xXXAction;
	}
}
