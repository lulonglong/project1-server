package locationshare.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locationshare.common.util.StringUtil;

import locationshare.action.LogInAction;
import locationshare.base.servlet.BaseServlet;

/**
 * Servlet implementation class UpdateBatchLocationServlet
 */
public class SignUpServlet extends BaseServlet {
	/** */
	private static final long serialVersionUID = 7392733831361287601L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String userAgent=req.getHeader("User-Agent");
		String usernameString = req.getParameter("username");
		String passwordString = req.getParameter("password");
		if (StringUtil.isNotNull(passwordString)) {
			try {
				passwordString = StringUtil.encodeBy16MD5(passwordString);
			} catch (NoSuchAlgorithmException e) {
				logger.error("MD5加密密码失败");
			}
		}
		String devicenameString = StringUtil.getDeviceName(userAgent);
		String phoneosString = StringUtil.getClientOS(userAgent);
		String registeripString = req.getRemoteAddr();
		
		return logInAction.signUp(null,usernameString, passwordString,
				registeripString, devicenameString, phoneosString);
	}

	private LogInAction logInAction;

	public void setLogInAction(LogInAction logInAction) {
		this.logInAction = logInAction;
	}

}
