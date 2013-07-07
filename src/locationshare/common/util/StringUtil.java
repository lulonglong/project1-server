package locationshare.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Descriptions
 * 
 * @version 2013-6-20
 * @author lulonglong
 * @since JDK1.6
 * 
 */
public class StringUtil {

	public static final String Empty = "";

	public static boolean isNotNull(String str) {

		boolean result = false;
		if (str != null && str.trim().length() > 0) {
			result = true;
		}

		return result;
	}

	/**
	 * ismobile
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMobile(String str) {
		String pattern = "1[3458]\\d{9}";
		return str.matches(pattern);
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmailAddress(String str) {
		String pattern = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		return m.matches();

	}

	/**
	 * @param str
	 * @return
	 */
	public static boolean isPositiveInteger(String str) {
		if (!isNotNull(str))
			return false;

		return Pattern.matches("\\d+", str.trim());
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		if (!isNotNull(str))
			return false;

		return Pattern.matches("-?\\d+", str.trim());
	}

	/**
	 * @param str
	 * @return
	 */
	public static boolean isPositiveDecimal(String str) {
		if (!isNotNull(str))
			return false;

		return Pattern.matches("\\d+\\.\\d+", str.trim());
	}

	/**
	 * @param str
	 * @return
	 */
	public static boolean isDecimal(String str) {
		if (!isNotNull(str))
			return false;

		return Pattern.matches("-?\\d+\\.\\d+", str.trim());
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPositiveNumber(String str) {
		return isPositiveDecimal(str) || isPositiveInteger(str);
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		return isDecimal(str) || isInteger(str);
	}

	/**
	 * md5
	 * 
	 * @param paraStr
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeByMD5(String paraStr)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		String result = null;
		char hexDigits[] = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };

		if (paraStr != null) {

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] source = paraStr.getBytes("utf-8");

			md.update(source);
			byte[] tmp = md.digest();

			char[] str = new char[32];
			for (int i = 0, j = 0; i < 16; i++) {

				byte b = tmp[i];

				str[j++] = hexDigits[b >>> 4 & 0xf];
				str[j++] = hexDigits[b & 0xf];
			}
			result = new String(str);
		}

		return result;
	}

	/**
	 * url decode
	 * 
	 * @param str
	 * @param code
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String urlDecode(String str, String code)
			throws UnsupportedEncodingException {
		return URLDecoder.decode(str, code);
	}

	/**
	 * url encode
	 * 
	 * @param str
	 * @param code
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String urlEncode(String str, String code)
			throws UnsupportedEncodingException {
		return URLEncoder.encode(str, code);
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrWhiteSpace(String str) {
		return !isNotNull(str);
	}

	public static Map<String, String> getUrlParas(String url) {
		int paraSplitIndex = url.indexOf('?');
		if (paraSplitIndex == -1)
			return null;

		String parasString = url.substring(paraSplitIndex + 1);

		Map<String, String> paraMap = new HashMap<String, String>();

		String[] paraArr = null;

		if (isNullOrWhiteSpace(parasString)) {
			return null;
		}

		paraArr = parasString.split("&");

		for (String para : paraArr) {

			String[] keyValue = null;
			keyValue = para.split("=", 2);

			if (paraMap.containsKey(keyValue[0]))
				continue;

			if (keyValue.length > 1) {
				paraMap.put(keyValue[0], keyValue[1]);
			} else {
				if (keyValue[0] != "") {
					paraMap.put(keyValue[0], "");
				}
			}

		}

		return paraMap;
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	public static String getExceptionStack(Exception e) {
		StackTraceElement[] stackTraceElements = e.getStackTrace();
		StringBuilder result = new StringBuilder(e.toString()
				.replace('\r', ' ').replace('\n', ' ')
				+ "@@@");

		for (int index = 0; index < stackTraceElements.length; index++) {
			result.append("at ")
					.append(stackTraceElements[index].getClassName())
					.append(".");
			result.append(stackTraceElements[index].getMethodName() + "(");
			result.append(stackTraceElements[index].getFileName() + ":");
			result.append(stackTraceElements[index].getLineNumber() + ")@@@");
		}

		return result.toString();
	}

	/**
	 * stringlist with split  e.g. 1s,we,ff,ff
	 * 
	 * @param str
	 * @param split
	 * @return
	 */
	public static boolean isListString(String str, String split) {

		if (isNullOrWhiteSpace(str) || str.endsWith(split)
				|| str.startsWith(split))
			return false;

		for (String item : str.split(split)) {
			if (isNullOrWhiteSpace(item))
				return false;
		}

		return true;
	}


	/**
	 *  is it a static webpage 
	 * @param urlString
	 * @return
	 */
	public static boolean isStaticPage(String urlString) {
		if (isNullOrWhiteSpace(urlString))
			return false;

		if (urlString.contains("jsp") || urlString.contains("html")
				|| urlString.contains("htm") || urlString.matches("^/$")) {
			return true;
		}
		
		return false;
	}

	public static boolean isMobileClient(String userAgentString) {
		// TODO Auto-generated method stub
		return true;
	}
}
