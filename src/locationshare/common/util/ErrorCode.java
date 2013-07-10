package locationshare.common.util;

public final class ErrorCode {

	// Common ErrorCode
	public static final String COMMON_ERROR = "000001";
	public static final String DB_CONNECTION_TIMEOUT = "000002";

	// ValidateRegister ErrorCode
	public static final String VALIDATE_USERNAME_EXIST = "010001";
	
	// SINUP ErrorCode
	public static final String SIGNUP_PASSWORD_NULL = "020104";
	public static final String SINUP_FAILED_ = "020001";
	public static final String SIGNUP_USERNAME_OCCUPY = "020002";
	
	// logIn ErrorCode
	public static final String LOGIN_PASSWORD_NULL = "030104";
	public static final String LOGIN_FAILED = "030001";
	
	// record logininfo
	public static final String LOGININFO_RECORD_FAILED = "040001";
	
	// record excepiton
	public static final String EXCEPTION_RECORD_FAILED = "050101";

}
