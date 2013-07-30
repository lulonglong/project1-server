package locationshare.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locationshare.action.ProfileAction;
import locationshare.base.servlet.BaseServlet;

/**
 * Servlet implementation class UpdateBatchLocationServlet
 */
public class GetHeadPortraitServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8001479727143245289L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String userid = req.getParameter("userid");
		String lastgettime=req.getParameter("lastgettime");
		return profileAction.getPortrait(userid,lastgettime);
	}

	private ProfileAction profileAction;

	public void setProfileAction(ProfileAction profileAction) {
		this.profileAction = profileAction;
	}

}
