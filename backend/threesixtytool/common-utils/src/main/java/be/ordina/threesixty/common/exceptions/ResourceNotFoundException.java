package be.ordina.threesixty.common.exceptions;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends FunctionalException {

	public ResourceNotFoundException(final Errorcode errorCode, final String message, final Throwable cause) {
		super(errorCode.name(), message, cause);
	}

	public enum Errorcode {
		GENERAL_RESOURCE_NOT_FOUND_EXCEPTION, NOT_YET_ACTIVE, NO_LONGER_ACTIVE, NO_SUBSCRIPTION, NO_CUSTOMERS_FOUND_FOR_USER, NO_CONTACT_INFO;
	}
}
