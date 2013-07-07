
package locationshare.validator;

import org.lulonglong.base.validator.AbstractParamValidator;

import locationshare.common.util.StringUtil;

/**
 * 
 * @version 2013-6-14
 * @author lulonglong
 * @since JDK1.6
 * 
 */
public class ListStringValidator extends AbstractParamValidator {

	private String split;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * locationshare.validator.AbstractParamValidator#isError(java.lang.String)
	 */
	@Override
	protected boolean isError(String content) throws Exception {
		return StringUtil.isNotNull(content)
				&& !StringUtil.isListString(content, split);
	}

	/**
	 * set split
	 * 
	 * @param split
	 */
	public void setSplit(String split) {
		this.split = split;
	}

}
