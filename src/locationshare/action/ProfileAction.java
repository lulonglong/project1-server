package locationshare.action;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import locationshare.base.action.BaseAction;
import locationshare.base.vo.BaseResultVO;
import locationshare.common.util.ErrorCode;
import locationshare.common.util.StringUtil;
import locationshare.hibernate.HibernateUtil;
import locationshare.hibernate.TbUserDetail;
import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.fileupload.FileItem;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.JDBCConnectionException;

import com.sina.sae.storage.SaeStorage;
import com.sina.sae.util.SaeUserInfo;

/**
 * Descriptions
 * 
 * @version 2013-7-7
 * @author lulonglong
 * @since JDK1.6
 * 
 */
/**
 * @author Administrator
 * 
 */
public class ProfileAction extends BaseAction {
	/**
	 * 更新个人资料
	 * 
	 * @param userid
	 * @param nickname
	 * @param address
	 * @param email
	 * @param phonenumber
	 * @param sex
	 * @param age
	 * @param school
	 * @return
	 */
	public String updateUserDetail(String userid, String nickname,
			String address, String email, String phonenumber, String sex,
			String age, String school) {
		BaseResultVO vo = new BaseResultVO();
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Query query = session
					.createQuery("update TbUserDetail detail set detail.nickname=:nickname,detail.email=:email,detail.address=:address,detail.school=:school,detail.sex=:sex,detail.age=:age,detail.telephone=:phonenumber where detail.userid=:userid");
			query.setString("userid", userid);
			query.setString("nickname", nickname);
			query.setString("email", email);
			query.setString("address", address);
			query.setString("school", school);
			query.setString("sex", sex);
			query.setString("age", age);
			query.setString("phonenumber", phonenumber);
			int count = query.executeUpdate();
			if (count == 0) {
				TbUserDetail userDetail = new TbUserDetail(
						Integer.parseInt(userid), nickname, email, address,
						school, sex, Integer.parseInt(age), phonenumber, null,
						null, null, null);
				session.save(userDetail);
				session.flush();
			}

			return vo.toSuccessJsonResult();

		} catch (JDBCConnectionException e) {
			logger.error("updateUserDetail Error:"
					+ StringUtil.getExceptionStack(e));
			return vo.toErrorJsonResult(ErrorCode.DB_CONNECTION_TIMEOUT);
		} finally {
			if (session != null)
				session.close();
		}
	}

	/**
	 * 更新签名
	 * 
	 * @param userid
	 * @param signature
	 * @return
	 */
	public String updateSignature(String userid, String signature) {
		BaseResultVO vo = new BaseResultVO();
		Session session = null;
		try {
			session = HibernateUtil.getSession();

			Query query = session
					.createQuery("update TbUserDetail detail set detail.signature=:signature where detail.userid=:userid");
			query.setString("userid", userid);
			query.setString("signature", signature);
			int count = query.executeUpdate();
			if (count == 0) {
				TbUserDetail userDetail = new TbUserDetail(
						Integer.parseInt(userid));
				userDetail.setSignature(signature);
				session.save(userDetail);
				session.flush();
			}

			return vo.toSuccessJsonResult();

		} catch (JDBCConnectionException e) {
			logger.error("updateUserDetail Error:"
					+ StringUtil.getExceptionStack(e));
			return vo.toErrorJsonResult(ErrorCode.DB_CONNECTION_TIMEOUT);
		} finally {
			if (session != null)
				session.close();
		}
	}

	public String updatePortrait(String userid, FileItem headportrait) {
		
		String fileName = headportrait.getName();
		String suffixString = StringUtil.getFileSuffix(fileName);
		String portraitFilename = userid + suffixString;
		String thumbnailFilename = userid + "-thumbnail" + suffixString;
		String tmpPortraitPath = SaeUserInfo.getSaeTmpPath() + "/"
				+ portraitFilename;
		String tmpThumbnailPath = SaeUserInfo.getSaeTmpPath() + "/"
				+ thumbnailFilename;

		BaseResultVO vo = new BaseResultVO();
		Session session = null;
		InputStream inputStream = null;
		BufferedOutputStream portraitOutputStream = null;
		FileInputStream portraitInputStream = null;
		FileInputStream thumbnailInputStream = null;

		try {
			// save portrait
			inputStream = headportrait.getInputStream();
			portraitOutputStream = new BufferedOutputStream(
					new FileOutputStream(tmpPortraitPath));

			byte[] buffer = new byte[10000];
			int count = 0;

			while ((count = inputStream.read(buffer)) != -1) {
				portraitOutputStream.write(buffer, 0, count);
			}
			portraitOutputStream.flush();

			// get thumbnail
			Thumbnails.of(tmpPortraitPath).size(96, 96)
					.toFile(tmpThumbnailPath);

			// upload portrait to storage
			portraitInputStream = new FileInputStream(tmpPortraitPath);
			ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
			while ((count = portraitInputStream.read(buffer)) != -1) {
				out.write(buffer, 0, count);
			}
			SaeStorage storage = new SaeStorage();
			storage.write("image", portraitFilename, out.toByteArray());

			// upload thumbnail to storage
			thumbnailInputStream = new FileInputStream(tmpThumbnailPath);
			out.reset();
			while ((count = thumbnailInputStream.read(buffer)) != -1) {
				out.write(buffer, 0, count);
			}
			storage.write("image", thumbnailFilename, out.toByteArray());

			// get storage portrait url
			String highVcard = storage.getUrl("image", portraitFilename);
			String lowVcard = storage.getUrl("image", thumbnailFilename);

			// save database
			session = HibernateUtil.getSession();
			Query query = session
					.createQuery("update TbUserDetail set highVcard=:highVcard,lowVcard=:lowVcard,vcardUpdateDate=:vcardUpdateDate where userid=:userid");
			query.setString("userid", userid);
			query.setString("lowVcard", lowVcard);
			query.setString("highVcard", highVcard);
			query.setTimestamp("vcardUpdateDate", new Date());
			
			int updatedCount = query.executeUpdate();
			if (updatedCount == 0) {
				
				TbUserDetail userDetail = new TbUserDetail(
						Integer.parseInt(userid));
				userDetail.setLowVcard(lowVcard);
				userDetail.setHighVcard(highVcard);
				userDetail.setVcardUpdateDate(new Date());
				session.save(userDetail);
				session.flush();
			}

			return vo.toSuccessJsonResult();

		} catch (IOException e) {
			logger.error("save image failed:" + StringUtil.getExceptionStack(e));
			return vo.toErrorJsonResult(ErrorCode.UPDATE_HEADPORTRAIT_FAILED);
			
		} finally {
			if (session != null)
				session.close();
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("close inputStream failed");
				}
			if (portraitOutputStream != null)
				try {
					portraitOutputStream.close();
				} catch (IOException e) {
					logger.error("close portraitOutputStream failed");
				}
			if (thumbnailInputStream != null)
				try {
					thumbnailInputStream.close();
				} catch (IOException e) {
					logger.error("close thumbnailInputStream failed");
				}
			if (portraitInputStream != null) {
				try {
					portraitInputStream.close();
				} catch (IOException e) {
					logger.error("close portraitInputStream failed");
				}
			}
		}
	}
}
