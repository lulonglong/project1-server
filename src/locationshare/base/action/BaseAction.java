package locationshare.base.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseAction {
	
	protected Log logger;
	
	public BaseAction() {
		logger = LogFactory.getLog(this.getClass());
	}
}
