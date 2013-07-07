package locationshare.validator;

import org.lulonglong.base.validator.AbstractParamValidator;

import locationshare.common.util.StringUtil;

/**
 * is null or whitespace
 * 
 * @version 2013-6-14
 * @author Suntec
 * @since JDK1.6
 * 
 */
public class NullValidator extends AbstractParamValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * locationshare.validator.AbstractParamValidator#isError(java.lang.String)
	 */
	@Override
	protected boolean isError(String content) throws Exception {
		return StringUtil.isNullOrWhiteSpace(content);
	}

}
