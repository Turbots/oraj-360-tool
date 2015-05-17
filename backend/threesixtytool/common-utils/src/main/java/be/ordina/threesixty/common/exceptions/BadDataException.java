package be.ordina.threesixty.common.exceptions;

@SuppressWarnings("serial")
public class BadDataException extends FunctionalException {

	public BadDataException(final ErrorCode errorCode, final String message, final Throwable cause) {
		super(errorCode.name(), message, cause);
	}

	public enum ErrorCode {
		GENERAL_BAD_DATA_EXCEPTION
	}
}
