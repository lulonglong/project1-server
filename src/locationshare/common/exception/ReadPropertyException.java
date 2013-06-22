package locationshare.common.exception;

public class ReadPropertyException extends Exception {

	/** */
	private static final long serialVersionUID = 419352142106123753L;

	public ReadPropertyException(String detailMessage){
		super("ReadPropertyException--"+detailMessage);
	}

}
