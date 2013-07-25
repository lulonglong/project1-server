package locationshare.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locationshare.action.ProfileAction;
import locationshare.base.servlet.BaseServlet;
import locationshare.base.vo.BaseResultVO;
import locationshare.common.util.ErrorCode;
import locationshare.common.util.StringUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UpdateBatchLocationServlet
 */
public class UpdateHeadPortraitServlet extends BaseServlet {
	private static final long serialVersionUID = -3062150377046319700L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String userid = req.getParameter("userid");
		BaseResultVO vo = new BaseResultVO();

		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			if (!ServletFileUpload.isMultipartContent(req)) {
				return vo.toErrorJsonResult(ErrorCode.HEADPORTRAIT_NULL);
			}

			FileItem headportrait = (FileItem) upload.parseRequest(req).get(0);
			if (headportrait.isFormField()) {
				return vo.toErrorJsonResult(ErrorCode.HEADPORTRAIT_NULL);
			}

			return profileAction.updatePortrait(userid, headportrait);

		} catch (Exception e) {
			logger.error("get image failed:" + StringUtil.getExceptionStack(e));
			return vo.toErrorJsonResult(ErrorCode.UPDATE_HEADPORTRAIT_FAILED);
		}

	}

	private ProfileAction profileAction;

	public void setProfileAction(ProfileAction profileAction) {
		this.profileAction = profileAction;
	}

}
