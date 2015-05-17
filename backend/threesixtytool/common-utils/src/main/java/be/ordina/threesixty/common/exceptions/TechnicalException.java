package be.ordina.threesixty.common.exceptions;

@SuppressWarnings("serial")
public class TechnicalException extends MicroserviceException {

	public TechnicalException(final Throwable cause) {
		super(cause);
	}

	public TechnicalException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public TechnicalException(final String errorCode, final String message, final Throwable cause) {
		super(errorCode, message, cause);
	}
}
