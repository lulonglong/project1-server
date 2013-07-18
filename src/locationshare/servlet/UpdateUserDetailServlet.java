package locationshare.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locationshare.action.ProfileAction;
import locationshare.base.servlet.BaseServlet;

/**
 * Servlet implementation class UpdateBatchLocationServlet
 */
public class UpdateUserDetailServlet extends BaseServlet {

	private static final long serialVersionUID = -5431476930506354355L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String userid = req.getParameter("userid");
		String nickname = req.getParameter("nickname");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String phonenumber = req.getParameter("phonenumber");
		String sex= req.getParameter("sex");
		String age = req.getParameter("age");
		String school = req.getParameter("school");
		return profileAction.updateUserDetail(userid,nickname,address,email,phonenumber,sex,age,school);
	}

	private ProfileAction profileAction;

	public void setProfileAction(ProfileAction profileAction) {
		this.profileAction = profileAction;
	}

}
