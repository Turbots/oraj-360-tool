package be.ordina.threesixty.common.error;

import static com.google.common.collect.Sets.newHashSet;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import be.ordina.threesixty.common.exceptions.MicroserviceException;
import be.ordina.threesixty.common.hateoas.EmbeddedResourceSupport;

import com.google.common.collect.Sets;

@Component
public class ExceptionToErrorResourceConverter {

	private static final String ERROR_REL = "errors";

	public ErrorResource convert(final Throwable exception) {
		ErrorResource errorResource = new ErrorResource();
		errorResource.setExceptionType(exception.getClass().getSimpleName());
		errorResource.setMessage(exception.getMessage());
		errorResource.setStackTrace(stacktraceToString(exception));
		return errorResource;
	}

	public ErrorResource convert(final MicroserviceException exception) {
		ErrorResource errorResource = new ErrorResource();
		errorResource.setCode(exception.getErrorCode());
		errorResource.setExceptionType(exception.getClass().getSimpleName());
		errorResource.setMessage(exception.getMessage());
		errorResource.setStackTrace(stacktraceToString(exception));
		return errorResource;
	}

	private String stacktraceToString(final Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString();
	}

	public <T extends EmbeddedResourceSupport> void addErrors(
			final List<Throwable> exceptions, final T resource) {
		Set<ErrorResource> errorResourceList;
		if (!isEmpty(exceptions)) {
			errorResourceList = newHashSet();
			for (Throwable exception : exceptions) {
				ErrorResource errorResource;
				if (exception instanceof MicroserviceException) {
					errorResource = convert((MicroserviceException) exception);
				} else {
					errorResource = convert(exception);
				}
				errorResourceList.add(errorResource);
			}
			resource.put(ERROR_REL, errorResourceList);
		}
	}
}