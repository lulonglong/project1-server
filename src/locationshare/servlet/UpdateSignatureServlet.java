package locationshare.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locationshare.action.ProfileAction;
import locationshare.base.servlet.BaseServlet;

/**
 * Servlet implementation class UpdateBatchLocationServlet
 */
public class UpdateSignatureServlet extends BaseServlet {

	private static final long serialVersionUID = 444471965201953239L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String userid= req.getParameter("userid");
		String signature = req.getParameter("signature");
		return profileAction.updateSignature(userid,signature);
	}

	private ProfileAction profileAction;

	public void setProfileAction(ProfileAction profileAction) {
		this.profileAction = profileAction;
	}

}
