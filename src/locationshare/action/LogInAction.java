
package locationshare.action;

import locationshare.base.action.BaseAction;
import locationshare.common.exception.ReadPropertyException;
import locationshare.common.util.LocalProperties;

/**
 * Descriptions
 * 
 * @version 2013-7-7
 * @author lulonglong
 * @since JDK1.6
 * 
 */
public class LogInAction extends BaseAction {

	private String mqPath;
	private String mqName;

	/**
	 * 发消息给所有人 * 
	 * @param msg
	 * @return
	 * @throws ReadPropertyException
	 */
	public boolean sendMsgToAll( String msg ) throws ReadPropertyException {
		readPropertyFile();
		return true;
	}

	/**
	 * 发消息给指定的人	 * 
	 * @param msg
	 * @param userListString
	 * @return
	 * @throws ReadPropertyException
	 */
	public boolean sendMsgToUser( String msg, String userListString ) throws ReadPropertyException {
		readPropertyFile();
		return true;
	}

	/**
	 * 发消息给指定�?	 * 
	 * @param msg
	 * @param groupListString
	 * @return
	 * @throws ReadPropertyException
	 */
	public boolean sendMsgToGroup( String msg, String groupListString ) throws ReadPropertyException {
		readPropertyFile();
		return true;
	}

	/**
	 * 读取配置文件中的MQ信息
	 * 
	 * @throws ReadPropertyException
	 */
	private void readPropertyFile() throws ReadPropertyException {
		mqPath = LocalProperties.getProperty( "connectionfactory.qpidConnectionfactory" );
		mqName = LocalProperties.getProperty( "destination.topicExchange" );
	}

}
