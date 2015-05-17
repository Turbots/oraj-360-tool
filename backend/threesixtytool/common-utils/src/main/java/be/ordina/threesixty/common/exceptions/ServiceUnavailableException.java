package be.ordina.threesixty.common.exceptions;

@SuppressWarnings("serial")
public class ServiceUnavailableException extends TechnicalException {

	public ServiceUnavailableException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ServiceUnavailableException(final ErrorCode errorCode, final Throwable cause) {
		super(errorCode.name(), errorCode.name(), cause);
	}

	public enum ErrorCode {
		TIMEOUT,
		SHORTCIRCUITED,
		TOO_MANY_CONNECTIONS
	}
}
