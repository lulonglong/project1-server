package locationshare.validator;

import locationshare.common.util.StringUtil;

import org.lulonglong.base.validator.AbstractParamValidator;

public class IntegerRange extends AbstractParamValidator {

	private int min = 0;
	private int max = Integer.MAX_VALUE;

	@Override
	protected boolean isError(String content) throws Exception {
		int targetInt = Integer.parseInt(content);
		return StringUtil.isNotNull(content)
				&& (targetInt < min || targetInt > max);
	}

	/**
	 * @param min
	 *            the min to set
	 */
	public void setMin(String min) {
		this.min = Integer.parseInt(min);
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(String max) {
		this.max = Integer.parseInt(max);
	}

}
