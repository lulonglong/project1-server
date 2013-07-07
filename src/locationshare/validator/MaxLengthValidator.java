
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
public class MaxLengthValidator extends AbstractParamValidator {

	private int maxLength;
	private String charsetName = "utf-8";


	@Override
	protected boolean isError( String content ) throws Exception {
		return StringUtil.isNotNull( content ) && content.getBytes( charsetName ).length > maxLength;
	}

	/**
	 * 
	 * @param charsetName
	 */
	public void setCharsetName( String charsetName ) {
		this.charsetName = charsetName;
	}

	/**
	 * 
	 * @param maxLength
	 */
	public void setMaxLength(String maxLength) {
		this.maxLength = Integer.parseInt(maxLength);
	}

}
