package locationshare.common.aspect;

import locationshare.common.util.StringUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Descriptions
 * 
 * @version 2013-6-20
 * @author lulonglong
 * @since JDK1.6
 * 
 */

public class LogAspect {
	private String level = "debug";

	/**
	 * 方法执行后的log
	 * 
	 * @param jp
	 */
	public void doAfterReturn(JoinPoint jp) {
		Log log = LogFactory.getLog(jp.getTarget().getClass());
		logMessage(log, level, jp.getSignature().getName() + ": end");
	}

	/**
	 * 环绕通知的log
	 * 
	 * @param jp
	 * @return
	 * @throws Throwable
	 */
	public Object doAround(ProceedingJoinPoint jp) throws Throwable {
		long time = System.currentTimeMillis();
		Object retVal = jp.proceed();
		time = System.currentTimeMillis() - time;

		Log log = LogFactory.getLog(jp.getTarget().getClass());

		logMessage(log, level, jp.getSignature().getName() + ": return = "
				+ retVal);
		logMessage(log, level, jp.getSignature().getName() + ": time cost = "
				+ time + " Milisecond" + "\r\n");

		return retVal;
	}

	/**
	 * 方法执行前的log
	 * 
	 * @param jp
	 */
	public void doBefore(JoinPoint jp) {
		Log log = LogFactory.getLog(jp.getTarget().getClass());
		logMessage(log, level, jp.getSignature().getName() + ": begin");

		// debug的场合，将parameter写入log
		if ("debug".equalsIgnoreCase(level)) {
			String[] argNames;
			try {
				Signature s = jp.getStaticPart().getSignature();
				MethodSignature ms = (MethodSignature) s;
				argNames = ms.getParameterNames();
			} catch (Exception e) {
				logMessage(log, level,

				StringUtil.getExceptionStack(e));
				argNames = null;
			}

			Object[] args = jp.getArgs();

			if (args.length > 0) {
				String suffix = StringUtil.Empty;

				logMessage(log, level,
						"list the parameters of the target method:");
				for (int i = 0; i < args.length; i++) {

					if (i == args.length - 1)
						suffix = "\r\n";

					if (argNames != null) {
						logMessage(log, level,

						argNames[i] + " = " + args[i] + suffix);
					} else {
						if (null == args[i]) {
							logMessage(log, level,

							"param[" + (i + 1) + "] = null" + suffix);
						} else {
							logMessage(log, level,

							"param[" + (i + 1) + "] = "
									+ args[i].getClass().getName() + args[i]
									+ suffix);
						}
					}
				}

			} else {
				logMessage(log, level,
						"list the parameters of the target method: NO PARAMETER\r\n");
			}
		}
	}

	/**
	 * 发生异常时的log
	 * 
	 * @param jp
	 * @param ex
	 */
	public void doThrowing(JoinPoint jp, Throwable ex) {
		Log log = LogFactory.getLog(jp.getTarget().getClass());
		logMessage(log, level, jp.getSignature().getName()
				+ ": exception happened:" + ex.getClass().getName());
		logMessage(log, level, ex.getMessage() + "\r\n");
	}


	/**
	 * 
	 * @return
	 */
	public String getLevel() {
		return level;
	}

	private void logMessage(Log log, String level, String message) {
		if ("debug".equalsIgnoreCase(level)) {
			log.debug(message);
		} else if ("info".equalsIgnoreCase(level)) {
			log.info(message);
		} else {
			// default is debug message
			log.debug(message);
		}
	}

	/**
	 * 
	 * @param level
	 */
	public void setLevel(String level) {
		this.level = level;
	}

}
