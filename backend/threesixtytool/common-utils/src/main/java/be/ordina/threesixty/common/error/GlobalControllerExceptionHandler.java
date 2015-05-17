package be.ordina.threesixty.common.error;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import static org.springframework.http.HttpStatus.FORBIDDEN;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.Ordered;

import org.springframework.core.annotation.Order;

import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;

import org.springframework.security.access.AccessDeniedException;

import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;

import be.ordina.threesixty.common.error.ExceptionToErrorResourceConverter;

import be.ordina.threesixty.common.exceptions.BadDataException;

import be.ordina.threesixty.common.exceptions.FunctionalException;

import be.ordina.threesixty.common.exceptions.ResourceNotFoundException;

import be.ordina.threesixty.common.exceptions.ServiceUnavailableException;

import be.ordina.threesixty.common.exceptions.TechnicalException;

import be.ordina.threesixty.common.error.ErrorResource;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalControllerExceptionHandler {

	@Autowired
	private ExceptionToErrorResourceConverter exceptionToErrorResourceConverter;

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorResource> handleAccessDeniedException(
			final AccessDeniedException exception) {

		ErrorResource errorResource = exceptionToErrorResourceConverter
				.convert(exception);

		return new ResponseEntity<ErrorResource>(errorResource,
				addContentType(), FORBIDDEN);

	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResource> handleResourceNotFoundException(
			final ResourceNotFoundException exception) {

		ErrorResource errorResource = exceptionToErrorResourceConverter
				.convert(exception);

		return new ResponseEntity<ErrorResource>(errorResource,
				addContentType(), NOT_FOUND);

	}

	@ExceptionHandler(BadDataException.class)
	public ResponseEntity<ErrorResource> handleBadDataException(
			final BadDataException exception) {

		ErrorResource errorResource = exceptionToErrorResourceConverter
				.convert(exception);

		return new ResponseEntity<ErrorResource>(errorResource,
				addContentType(), BAD_REQUEST);

	}

	@ExceptionHandler(TechnicalException.class)
	public ResponseEntity<ErrorResource> handleTechnicalException(
			final TechnicalException exception) {

		ErrorResource errorResource = exceptionToErrorResourceConverter
				.convert(exception);

		return new ResponseEntity<ErrorResource>(errorResource,
				addContentType(), INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(FunctionalException.class)
	public ResponseEntity<ErrorResource> handleFunctionalException(
			final FunctionalException exception) {

		ErrorResource errorResource = exceptionToErrorResourceConverter
				.convert(exception);

		return new ResponseEntity<ErrorResource>(errorResource,
				addContentType(), INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(ServiceUnavailableException.class)
	public ResponseEntity<ErrorResource> handleServiceUnavailableException(
			final ServiceUnavailableException exception) {

		ErrorResource errorResource = exceptionToErrorResourceConverter
				.convert(exception);

		return new ResponseEntity<ErrorResource>(errorResource,
				addContentType(), SERVICE_UNAVAILABLE);

	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResource> handleRuntimeException(
			final RuntimeException exception) {

		ErrorResource errorResource = exceptionToErrorResourceConverter
				.convert(exception);

		return new ResponseEntity<ErrorResource>(errorResource,
				addContentType(), INTERNAL_SERVER_ERROR);

	}

	private HttpHeaders addContentType() {

		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Type", "application/vnd.error+json");

		return headers;

	}

}
