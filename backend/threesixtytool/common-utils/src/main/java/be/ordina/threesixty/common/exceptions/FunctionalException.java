package be.ordina.threesixty.common.exceptions;

@SuppressWarnings("serial")
public class FunctionalException extends MicroserviceException {

	public FunctionalException(final Throwable cause) {
		super(cause);
	}

	public FunctionalException(final String errorCode, final String message, final Throwable cause) {
		super(errorCode, message, cause);
	}
}
