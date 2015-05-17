package be.ordina.threesixty.common.error;

import java.util.HashSet;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.apache.commons.lang3.builder.EqualsBuilder;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.springframework.hateoas.ResourceSupport;

import be.ordina.threesixty.common.hateoas.EmbeddedResourceSupport;

@JsonInclude(Include.NON_NULL)
public class ErrorResource extends EmbeddedResourceSupport {

	private String exceptionType;
	private String message;
	private String code;
	private String stackTrace;

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(final String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(final String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public void addError(final ResourceSupport error) {
		if (hasEmbeddedResources("errors")) {
			getEmbeddedResourcesByRel("errors").add(error);
		} else {
			Set<ResourceSupport> errors = new HashSet<ResourceSupport>();
			errors.add(error);
			put("errors", errors);
		}
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(exceptionType)
		.append(message)
		.append(code)
		.append(stackTrace)
		.append(getLinks())
		.toHashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof ErrorResource) {
			final ErrorResource other = (ErrorResource) obj;
			return new EqualsBuilder()
			.append(exceptionType, other.exceptionType)
			.append(message, other.message)
			.append(code, other.code)
			.append(stackTrace, other.stackTrace)
			.append(getLinks(), other.getLinks())
			.isEquals();
		} else {
			return false;
		}
	}
}