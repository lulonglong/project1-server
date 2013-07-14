package locationshare.common.util;

public final class ErrorCode {

	// Common ErrorCode
	public static final String COMMON_ERROR = "0000001";
	public static final String DB_CONNECTION_TIMEOUT = "0000002";

	// ValidateRegister ErrorCode
	public static final String VALIDATE_USERNAME_EXIST = "0101001";
	
	// SINUP ErrorCode
	public static final String SIGNUP_PASSWORD_NULL = "0102104";
	public static final String SINUP_FAILED_ = "0102001";
	public static final String SIGNUP_USERNAME_OCCUPY = "0102002";
	
	// logIn ErrorCode
	public static final String LOGIN_PASSWORD_NULL = "0103104";
	public static final String LOGIN_FAILED = "0103001";
	
	// record logininfo
	public static final String LOGININFO_RECORD_FAILED = "0104001";
	
	// record excepiton
	public static final String EXCEPTION_RECORD_FAILED = "0105101";

}
