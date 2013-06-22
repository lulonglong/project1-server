package locationshare.base.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseDAO{
	
	protected Log logger;
	
	public BaseDAO() {
		logger = LogFactory.getLog(this.getClass());		
	}	
}
