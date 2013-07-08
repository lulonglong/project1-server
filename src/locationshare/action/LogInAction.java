package locationshare.action;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.JDBCConnectionException;

import locationshare.base.action.BaseAction;
import locationshare.base.vo.BaseResultVO;
import locationshare.common.util.ErrorCode;
import locationshare.common.util.StringUtil;
import locationshare.hibernate.HibernateUtil;
import locationshare.hibernate.TbException;
import locationshare.hibernate.TbUser;
import locationshare.vo.LogInResultVo;

/**
 * Descriptions
 * 
 * @version 2013-7-7
 * @author lulonglong
 * @since JDK1.6
 * 
 */
public class LogInAction extends BaseAction {

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
	 * sign up
	 * 
	 * @param type
	 *            1:QQ,2:sina
	 * @param username
	 * @param password
	 * @return
	 */
	public String signUp(String type, String username, String password,
			String ip, String devicename, String phoneos) {
		Session session = null;
		LogInResultVo vo = new LogInResultVo();
		try {

			session = HibernateUtil.getSession();
			TbUser user = new TbUser(new Date(), ip, devicename, phoneos);
			if (StringUtil.isNullOrWhiteSpace(type) || type.equals("0")) {
				user.setUsername(username);
				user.setPassword(password);
			} else if (type.equals("1")) {
				user.setQq(username);
			} else if (type.equals("2")) {
				user.setSina(username);
			}

			session.save(user);
			return vo.toSuccessJsonResult(user.getUserid());

		} catch (JDBCConnectionException e) {
			logger.error("signUp Error:" + StringUtil.getExceptionStack(e));
			return vo.toErrorJsonResult(ErrorCode.DB_CONNECTION_TIMEOUT);
		} finally {
			if (session != null)
				session.close();
		}
	}

}
