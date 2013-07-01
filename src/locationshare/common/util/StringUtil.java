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
	 * 判断是否手机号码
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMobile(String str) {
		String pattern = "\\d+";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 判断是否邮件地址 合法E-mail地址： 1. 必须包含一个并且只有一个符号“@” 2. 第一个字符不得是“@”或者“.” 3.
	 * 不允许出现“@.”或者.@ 4. 结尾不得是字符“@”或者“.” 5. 允许“@”前的字符中出现“＋” 6. 不允许“＋”在最前面，或者“＋@”
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
	 * 判断是否为整数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPositiveInteger(String str) {
		if (!isNotNull(str))
			return false;

		return Pattern.matches("\\d+", str.trim());
	}

	/**
	 * 判断是否为整数
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
	 * 判断是否为小数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPositiveDecimal(String str) {
		if (!isNotNull(str))
			return false;

		return Pattern.matches("\\d+\\.\\d+", str.trim());
	}

	/**
	 * 判断是否为小数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDecimal(String str) {
		if (!isNotNull(str))
			return false;

		return Pattern.matches("-?\\d+\\.\\d+", str.trim());
	}

	/**
	 * 是否为正数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPositiveNumber(String str) {
		return isPositiveDecimal(str) || isPositiveInteger(str);
	}

	/**
	 * 是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		return isDecimal(str) || isInteger(str);
	}

	/**
	 * md5加密字符串
	 * 
	 * @param paraStr
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeByMD5(String paraStr)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		String result = null;
		char hexDigits[] = {// 用来将字节转换成 16 进制表示的字符
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };

		if (paraStr != null) {

			// 返回实现指定摘要算法的 MessageDigest 对象
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] source = paraStr.getBytes("utf-8");

			// 使用指定的 byte 数组更新摘要
			md.update(source);
			byte[] tmp = md.digest();

			// 用16进制数表示需要32位
			char[] str = new char[32];
			for (int i = 0, j = 0; i < 16; i++) {// j表示转换结果中对应的字符位置， 从第一个字节开始，对
													// MD5 的每一个字节 转换成 16 进制字符

				byte b = tmp[i];

				// 取字节中高 4 位的数字转换
				str[j++] = hexDigits[b >>> 4 & 0xf];
				// 取字节中低 4 位的数字转换
				str[j++] = hexDigits[b & 0xf];
			}
			result = new String(str);// 结果转换成字符串用于返回
		}

		return result;
	}

	/**
	 * url解码
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
	 * url编码
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
	 * 判断是否为空值
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

		// 每个键值为一组
		paraArr = parasString.split("&");

		for (String para : paraArr) {

			String[] keyValue = null;
			keyValue = para.split("=", 2);

			if (paraMap.containsKey(keyValue[0]))
				continue;

			// 解析出键值
			if (keyValue.length > 1) {
				// 正确解析
				paraMap.put(keyValue[0], keyValue[1]);
			} else {
				if (keyValue[0] != "") {
					// 只有参数没有值，不加入
					paraMap.put(keyValue[0], "");
				}
			}

		}

		return paraMap;
	}

	/**
	 * 获取堆栈信息
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
	 * 判断是否被split分割的列表字符串 如：1s,we,ff,ff
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
	 * 判断是否为支持的移动客户端
	 * 
	 * @param userAgentString
	 * @return
	 */
	public static boolean isMobileClient(String userAgentString) {
		return true;
	}

	public static boolean isStaticPage(String urlString) {
		if (isNullOrWhiteSpace(urlString))
			return false;

		if (urlString.contains("jsp") || urlString.contains("html")
				|| urlString.contains("htm") || urlString.matches("^/[^/]+/$")) {
			return true;
		}
		
		return false;
	}
}
