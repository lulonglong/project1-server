package locationshare.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import locationshare.vo.LogInResultVo;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.JDBCConnectionException;

import antlr.StringUtils;

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
public class LogInAction extends BaseAction {
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

		if (StringUtil.isNullOrWhiteSpace(password)
				&& (StringUtil.isNullOrWhiteSpace(type) || type.equals("0"))) {
			return vo.toErrorJsonResult(ErrorCode.SIGNUP_PASSWORD_NULL);
		}

		try {

			session = HibernateUtil.getSession();
			TbUser user = new TbUser(new Date(), ip, devicename, phoneos);
			Criteria criteria = session.createCriteria(TbUser.class);

			if (StringUtil.isNullOrWhiteSpace(type) || type.equals("0")) {
				user.setUsername(username);
				user.setPassword(password);
				criteria.add(Restrictions.eq("username", username));
			} else if (type.equals("1")) {
				user.setQq(username);
				criteria.add(Restrictions.eq("qq", username));
			} else if (type.equals("2")) {
				user.setSina(username);
				criteria.add(Restrictions.eq("sina", username));
			}

			if (criteria.uniqueResult() != null)
				return vo.toErrorJsonResult(ErrorCode.SIGNUP_USERNAME_OCCUPY);

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

	/**
	 * login
	 * 
	 * @param type
	 * @param username
	 * @param password
	 * @return
	 */
	public String logIn(String type, String username, String password) {
		Session session = null;
		LogInResultVo vo = new LogInResultVo();

		if (StringUtil.isNullOrWhiteSpace(password)
				&& (StringUtil.isNullOrWhiteSpace(type) || type.equals("0"))) {
			return vo.toErrorJsonResult(ErrorCode.LOGIN_PASSWORD_NULL);
		}

		try {
			session = HibernateUtil.getSession();
			Criteria criteria = session.createCriteria(TbUser.class);
			if (StringUtil.isNullOrWhiteSpace(type) || type.equals("0")) {
				criteria.add(Restrictions.eq("username", username));
				criteria.add(Restrictions.eq("password", password));
			} else if (type.equals("1")) {
				criteria.add(Restrictions.eq("qq", username));
			} else if (type.equals("2")) {
				criteria.add(Restrictions.eq("sina", username));
			}

			List<TbUser> users = criteria.list();
			if (!users.isEmpty()) {
				return vo.toSuccessJsonResult(users.get(0).getUserid());
			} else {
				return vo.toErrorJsonResult(ErrorCode.LOGIN_FAILED);
			}

		} catch (JDBCConnectionException e) {
			logger.error("login Error:" + StringUtil.getExceptionStack(e));
			return vo.toErrorJsonResult(ErrorCode.DB_CONNECTION_TIMEOUT);
		} finally {
			if (session != null)
				session.close();
		}
	}

	/**
	 * recordException
	 * 
	 * @param userid
	 * @param exception
	 * @param phoneos
	 * @return
	 */
	public String recordException(String userid, String exception,
			String phoneos,String appversion,String dateStr) {
		Session session = null;
		LogInResultVo vo = new LogInResultVo();
		try {
			session = HibernateUtil.getSession();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null ;
			try {
				date = sdf.parse(dateStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return vo.toErrorJsonResult(ErrorCode.EXCEPTION_RECORD_FAILED);
			}
			TbException tbException = new TbException(Integer.parseInt(userid),
					date,exception,phoneos,appversion) ;

			session.save(tbException) ;
			return vo.toSuccessJsonResult(0);
		} catch (JDBCConnectionException e) {
			// TODO: handle exception
			logger.error("login Error:" + StringUtil.getExceptionStack(e));
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
			return vo.toErrorJsonResult(ErrorCode.DB_CONNECTION_TIMEOUT);
		} finally {
			if (session != null)
				session.close();
		}
	}
}
