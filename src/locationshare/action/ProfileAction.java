package locationshare.action;

import java.util.Date;
import java.util.List;

import locationshare.base.action.BaseAction;
import locationshare.base.vo.BaseResultVO;
import locationshare.common.util.ErrorCode;
import locationshare.common.util.StringUtil;
import locationshare.hibernate.HibernateUtil;
import locationshare.hibernate.TbException;
import locationshare.hibernate.TbLogininfo;
import locationshare.hibernate.TbUser;
import locationshare.hibernate.TbUserDetail;
import locationshare.vo.LogInResultVo;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.JDBCConnectionException;

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
	// TODO improve db performance
	/**
	 * is the username existing
	 * 
	 * @param usernameString
	 * @return
	 */
	public String validateRegister(String usernameString) {
		BaseResultVO vo = new BaseResultVO();
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Criteria criteria = session.createCriteria(TbUser.class);
			criteria.add(Restrictions.eq("username", usernameString));

			if (criteria.uniqueResult() == null)
				return vo.toSuccessJsonResult();
			return vo.toErrorJsonResult(ErrorCode.VALIDATE_USERNAME_EXIST);
		} catch (JDBCConnectionException e) {
			logger.error("validateRegister Error:"
					+ StringUtil.getExceptionStack(e));
			return vo.toErrorJsonResult(ErrorCode.DB_CONNECTION_TIMEOUT);
		} finally {
			if (session != null)
				session.close();
		}
	}

	/**
	 * recordLoginInfo
	 * 
	 * @param userid
	 * @param devicename
	 * @param phoneos
	 * @param ip
	 * @return
	 */
	public String recordLoginInfo(String userid, String devicename,
			String phoneos, String ip) {
		BaseResultVO vo = new BaseResultVO();
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			TbLogininfo logininfo = new TbLogininfo(Integer.parseInt(userid),
					new Date(), devicename, ip, phoneos);
			session.save(logininfo);
			return vo.toSuccessJsonResult();
		} catch (JDBCConnectionException e) {
			logger.error("validateRegister Error:"
					+ StringUtil.getExceptionStack(e));
			return vo.toErrorJsonResult(ErrorCode.LOGININFO_RECORD_FAILED);
		} finally {
			if (session != null)
				session.close();
		}
	}

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
			query.setParameter("userid", userid);
			query.setParameter("nickname", nickname);
			query.setParameter("email", email);
			query.setParameter("address", address);
			query.setParameter("school", school);
			query.setParameter("sex", sex);
			query.setParameter("age", age);
			query.setParameter("phonenumber", phonenumber);
			int count = query.executeUpdate();
			if (count == 0) {
				TbUserDetail userDetail = new TbUserDetail(
						Integer.parseInt(userid), nickname, email, address,
						school, sex, Integer.parseInt(age), phonenumber, null,
						null, null, null);
				session.save(userDetail);
			}

			return vo.toSuccessJsonResult();

		} catch (JDBCConnectionException e) {
			logger.error("updateUserDetail Error:"
					+ StringUtil.getExceptionStack(e));
			return vo.toErrorJsonResult(ErrorCode.UPDATE_USERDETAIL_FAILED);
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
			
			Query query = session.createQuery("update TbUserDetail detail set detail.signature=:signature where detail.userid=:userid");
			query.setString("userid", userid);
			query.setString("signature", signature);
			int count = query.executeUpdate();
			if (count == 0) {
				TbUserDetail userDetail = new TbUserDetail(Integer.parseInt(userid));
				userDetail.setSignature("dd");
				session.save(userDetail);
			}

			return vo.toSuccessJsonResult();

		} catch (JDBCConnectionException e) {
			logger.error("updateUserDetail Error:"
					+ StringUtil.getExceptionStack(e));
			return vo.toErrorJsonResult(ErrorCode.UPDATE_SIGNATURE_FAILED);
		}  finally {
			if (session != null)
				session.close();
		}
	}
}
