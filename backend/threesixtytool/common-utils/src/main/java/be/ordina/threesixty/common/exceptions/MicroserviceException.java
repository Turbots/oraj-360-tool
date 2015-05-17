package be.ordina.threesixty.common.exceptions;

@SuppressWarnings("serial")
public class MicroserviceException extends RuntimeException {

	private String errorCode;

	public MicroserviceException(final Throwable cause) {
		super(cause);
	}

	public MicroserviceException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public MicroserviceException(final String errorCode, final String message, final Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(final String errorCode) {
		this.errorCode = errorCode;
	}
}
