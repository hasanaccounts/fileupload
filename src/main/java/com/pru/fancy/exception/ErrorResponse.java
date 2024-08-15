package com.pru.fancy.exception;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pru.fancy.exception.Error.SeverityEnum;

/**
 * ErrorResponse
 */
//@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-11-08T15:42:31.083+05:30")
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("fatalError")
	private Boolean fatalError = null;

	@JsonProperty("error")
	private Error error = null;

	public ErrorResponse fatalError(Boolean fatalError) {
		this.fatalError = fatalError;
		return this;
	}

	public static ErrorResponse instance(SeverityEnum sev, Exception ex, final String message, final String code,
			final String status, boolean isFatal,final String errorCode) {
		final ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setFatalError(isFatal);
		final Error error = new Error();
		error.setCode(code);
		error.setSeverity(sev);
		error.setDescription(message);
		errorResponse.setError(error);
		if (ex != null) {
			final StringWriter sw = new StringWriter();
			final PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			final String trace = sw.toString();
			AdditionalDetails additionalDetailsItem = new AdditionalDetails();
			additionalDetailsItem.setDescription(trace);
			additionalDetailsItem.setErrorCode(errorCode);
			additionalDetailsItem.setSeverity(sev.toString());
			additionalDetailsItem.setStatus(status);
			error.addAdditionalDetailsItem(additionalDetailsItem);
		}
		return errorResponse;
	}

	public static ErrorResponse instance(SeverityEnum sev, final String message, final String code,
			final String description, final String status, boolean isFatal,final String errorCode) {
		final ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setFatalError(isFatal);
		final Error error = new Error();
		error.setCode(code);
		error.setSeverity(sev);
		error.setDescription(message);
		errorResponse.setError(error);
		AdditionalDetails additionalDetailsItem = new AdditionalDetails();
		additionalDetailsItem.setDescription(description);
		additionalDetailsItem.setErrorCode(errorCode);
		additionalDetailsItem.setSeverity(sev.toString());
		additionalDetailsItem.setStatus(status);
		error.addAdditionalDetailsItem(additionalDetailsItem);
		return errorResponse;
	}

	/**
	 * Get fatalError
	 * 
	 * @return fatalError
	 **/
	public Boolean getFatalError() {
		return fatalError;
	}

	public void setFatalError(Boolean fatalError) {
		this.fatalError = fatalError;
	}

	public ErrorResponse error(Error error) {
		this.error = error;
		return this;
	}

	/**
	 * Get error
	 * 
	 * @return error
	 **/
	@Valid
	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ErrorResponse errorResponse = (ErrorResponse) o;
		return Objects.equals(this.fatalError, errorResponse.fatalError)
				&& Objects.equals(this.error, errorResponse.error);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fatalError, error);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ErrorResponse {\n");

		sb.append("    fatalError: ").append(toIndentedString(fatalError)).append("\n");
		sb.append("    error: ").append(toIndentedString(error)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
