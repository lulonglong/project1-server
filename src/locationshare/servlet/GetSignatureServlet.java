package locationshare.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locationshare.action.ProfileAction;
import locationshare.base.servlet.BaseServlet;

/**
 * Servlet implementation class UpdateBatchLocationServlet
 */
public class GetSignatureServlet extends BaseServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2114537266945912499L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String userid = req.getParameter("userid");
		return profileAction.getSignature(userid);
	}

	private ProfileAction profileAction;

	public void setProfileAction(ProfileAction profileAction) {
		this.profileAction = profileAction;
	}

}
