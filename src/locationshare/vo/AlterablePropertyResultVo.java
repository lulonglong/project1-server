package locationshare.vo;

import java.text.MessageFormat;

import locationshare.base.vo.BaseResultVO;

public class AlterablePropertyResultVo extends BaseResultVO {

	public static final String successTemplate = "'{'\"code\":\"0\"{0}'}'";
	private StringBuilder contentString = new StringBuilder();

	@Override
	public String toSuccessJsonResult() {
		return MessageFormat.format(successTemplate, contentString.toString());
	}

	public void addProperty(String key, String value) {
		contentString.append(",\"").append(key).append("\":\"").append(value)
				.append("\"");
	}
}
