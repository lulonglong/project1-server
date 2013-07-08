package locationshare.validator;

import locationshare.common.util.StringUtil;

import org.lulonglong.base.validator.AbstractParamValidator;

/**
 * 可以用来验证任何正则表达式可以验证的东西
 * @author lulonglong
 *
 */
public class RegexValidator extends AbstractParamValidator {

	private String pattern=null;
	
	@Override
	protected boolean isError(String content) throws Exception {
		return StringUtil.isNotNull(content)&&!content.matches(pattern);
	}
	
	/**设置正则表达式
	 * @param pattern the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
