package locationshare.validator;

import locationshare.common.util.StringUtil;

import org.lulonglong.base.validator.AbstractParamValidator;

/**
 * match the regex pattern
 * @author lulonglong
 *
 */
public class RegexValidator extends AbstractParamValidator {

	private String pattern=null;
	
	@Override
	protected boolean isError(String content) throws Exception {
		return StringUtil.isNotNull(content)&&!content.matches(pattern);
	}
	
	/**set regex
	 * @param pattern the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
