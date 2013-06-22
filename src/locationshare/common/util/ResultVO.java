
package locationshare.common.util;

import java.util.List;


/**
 * Descriptions
 * 
 * @version 2013-6-20
 * @author lulonglong
 * @since JDK1.6
 * 
 */
public class ResultVO {

	/**
	 * 返回单个错误码的xml串
	 * 
	 * @return
	 */
	public static String toErrorXmlResult(String errorCode) {
		if (StringUtil.isNullOrWhiteSpace(errorCode))
			return null;

		return "<result code=\"1\"><error code=\"" + errorCode
				+ "\"/></result>";
	}

	/**
	 * 返回多个错误码的xml串
	 * 
	 * @return
	 */
	public static String toErrorXmlResult(List<String> errorCodeList) {
		if (errorCodeList == null || errorCodeList.isEmpty())
			return null;

		StringBuilder midString = new StringBuilder();

		for (String errorCode : errorCodeList) {
			midString.append("<error code=\"" + errorCode + "\"/>");
		}

		return "<result code=\"1\">" + midString + "</result>";
	}

	/**
	 * 返回单个错误码的Json串
	 * 
	 * @return
	 */
	public static String toErrorJsonResult(String errorCode) {
		if (StringUtil.isNullOrWhiteSpace(errorCode))
			return null;

		return "{\"code\":\"1\",\"errorCode\":[\"" + errorCode + "\"]}";
	}

	/**
	 * 返回错误码列表的Json串
	 * 
	 * @return
	 */
	public static String toErrorJsonResult(List<String> errorCodeList) {

		if (errorCodeList == null || errorCodeList.isEmpty())
			return null;

		StringBuilder midString = new StringBuilder();

		for (String errorCode : errorCodeList) {
			midString.append(",").append("\"" + errorCode + "\"");
		}

		midString.deleteCharAt(0);

		return "{\"code\":\"1\",\"errorCode\":[" + midString + "]}";
	}

	/**
	 * 带有返回码的结果
	 * 
	 * @param xmlContent
	 * @return
	 */
	public static String toSuccessXMLResult(String xmlContent) {
		return "<result code=\"0\">" + xmlContent + "</result>";
	}

	/**
	 * 带有返回码的结果
	 * 
	 * @param jsonContent
	 *            表示json格式的内容，如果内容仅仅表示一个字符串则采用\"abcdefg\"格式,即参数内容本身要包含双引号
	 * @return
	 */
	public static String toSucessJsonResult(String jsonContent) {
		if (StringUtil.isNullOrWhiteSpace(jsonContent))
			jsonContent = "{}";
		return "{\"code\"=\"0\",\"content\"=" + jsonContent + "}";
	}
}
