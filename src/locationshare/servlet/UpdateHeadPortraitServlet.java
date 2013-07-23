package locationshare.servlet;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import locationshare.action.ProfileAction;
import locationshare.base.servlet.BaseServlet;

/**
 * Servlet implementation class UpdateBatchLocationServlet
 */
public class UpdateHeadPortraitServlet extends BaseServlet {
	private static final long serialVersionUID = -3062150377046319700L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		String userid = req.getParameter("userid");
		ServletInputStream inputStream = null;
		BufferedOutputStream outputStream = null;

		try {
			inputStream = req.getInputStream();
			outputStream = new BufferedOutputStream(new FileOutputStream(
					"D:/1.png"));
			byte[] buffer = new byte[10000];
			int count = 0;

			while ((count = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, count);
			}
			outputStream.flush();

		} catch (Exception e) {
			logger.error("get image failed");
		} finally {
			if (inputStream != null)
				inputStream.close();
			if (outputStream != null)
				outputStream.close();
		}

		return "profile";
	}

	private ProfileAction profileAction;

	public void setProfileAction(ProfileAction profileAction) {
		this.profileAction = profileAction;
	}

}
