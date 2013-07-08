
package locationshare.base.vo;

import java.util.List;

import locationshare.common.util.StringUtil;


/**
 * Descriptions
 * 
 * @version 2013-6-20
 * @author lulonglong
 * @since JDK1.6
 * 
 */
public class BaseResultVO {

	/**
	 * xml result with single errorCode 
	 * 
	 * @return
	 */
	public  String toErrorXmlResult(String errorCode) {
		if (StringUtil.isNullOrWhiteSpace(errorCode))
			return null;

		return "<result code=\"1\"><error code=\"" + errorCode
				+ "\"/></result>";
	}

	/**
	 * xml result with multiple errorCode
	 * 
	 * @return
	 */
	public  String toErrorXmlResult(List<String> errorCodeList) {
		if (errorCodeList == null || errorCodeList.isEmpty())
			return null;

		StringBuilder midString = new StringBuilder();

		for (String errorCode : errorCodeList) {
			midString.append("<error code=\"" + errorCode + "\"/>");
		}

		return "<result code=\"1\">" + midString + "</result>";
	}

	/**
	 * Json result with single errorCode
	 * 
	 * @return
	 */
	public  String toErrorJsonResult(String errorCode) {
		if (StringUtil.isNullOrWhiteSpace(errorCode))
			return null;

		return "{\"code\":\"1\",\"errorCode\":[\"" + errorCode + "\"]}";
	}

	/**
	 * Json result with multiple errorCode
	 * 
	 * @return
	 */
	public  String toErrorJsonResult(List<String> errorCodeList) {

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
	 * successful xml result
	 * 
	 * @param xmlContent
	 * @return
	 */
	public  String toSuccessXMLResult() {
		return "<result code=\"0\"></result>";
	}
	
	/**
	 * successful xml result with content
	 * 
	 * @param xmlContent
	 * @return
	 */
	public  String toSuccessXMLResult(String xmlContent) {
		return "<result code=\"0\">" + xmlContent + "</result>";
	}

	/**
	 * successful json result
	 * 
	 * @return
	 */
	public  String toSuccessJsonResult() {
		return "{\"code\"=\"0\"}";
	}
	
}
