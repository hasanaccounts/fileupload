package com.pru.fancy.exception;

import java.io.IOException;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.pru.fancy.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pru.fancy.exception.Error.SeverityEnum;

/**
 * 
 * FileuploadapiExceptionHandler is a controller advice which captures any of the exceptions in the process of api execution.
 *
 */
@ControllerAdvice
public class FileuploadapiExceptionHandler extends ResponseEntityExceptionHandler{

	private static final Logger log = LoggerFactory.getLogger(FileuploadapiExceptionHandler.class);

	private static final String FAILED = "FAILED";
	
	private static final String ERROR = "global Error occured in controller, cause:";



	private HttpHeaders getResponseHeaders(WebRequest request) {
		// get header values
		// response headers created first
		final MultiValueMap<String, String> map = buildResponseMap();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.putAll(map);
		return httpHeaders;
	}

	private MultiValueMap<String, String> buildResponseMap() {
		final MultiValueMap<String, String> map;
		map = new LinkedMultiValueMap<>();		
		return map;
	}

	@ExceptionHandler(value = { 
			ConstraintViolationException.class,
			IllegalArgumentException.class })
	protected ResponseEntity<Object> handleRequestException(Exception exception, WebRequest request){
		// build header responses
		final HttpHeaders httpHeaders = getResponseHeaders(request);
		//Building API specific Exception Response Body
		// building default error response
		ErrorResponse errorResponse = ErrorResponse.instance(SeverityEnum.ERROR, exception, exception.getMessage(),ErrorStatusCode.BAD_REQUEST.getCode()+"", FAILED, true, String.valueOf(HttpStatus.BAD_REQUEST.value()));		
		// global log
		log.error(ERROR + exception.getMessage(), exception);
		// send error response
		return ResponseEntity.status(ErrorStatusCode.BAD_REQUEST.getCode()).headers(httpHeaders).contentType(MediaType.APPLICATION_JSON).body((Object)errorResponse);
	}

	/**
	 * FileuploadapiException handler
	 * @param FileuploadapiException
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = { FileuploadapiException.class })
	protected ResponseEntity<Object> handleRequestException(FileuploadapiException exception, WebRequest request) {
		// build header responses
		final HttpHeaders httpHeaders = getResponseHeaders(request);

		// send error response
		return ResponseEntity.status(exception.getHttpCode()).headers(httpHeaders).contentType(MediaType.APPLICATION_JSON)
				.body((Object)exception.getErrorResponse());
	}

	/**
	 * Internal server Error -500
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Object> handleGeneralException(Exception exception, WebRequest request) {
		// build error response
		final ErrorResponse errorResponse;
		// build headers
		final HttpHeaders httpHeaders = getResponseHeaders(request);
		//Building API specific Exception Response Body
		// global log
		log.error(ERROR + exception.getMessage(), exception);		
		// building default error response
		errorResponse = ErrorResponse.instance(SeverityEnum.ERROR, exception, exception.getMessage(),""+ErrorStatusCode.INTERNAL_SERVER_ERROR.getCode(), FAILED, true,String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));

		// send error response
		return ResponseEntity.status(ErrorStatusCode.INTERNAL_SERVER_ERROR.getCode()).headers(httpHeaders).contentType(MediaType.APPLICATION_JSON).body((Object)errorResponse);
	}

	/**
	 * Http Server Error
	 * @param HttpStatusCodeException httpStatusCodeException
	 * @param WebRequest request
	 * @return ResponseEntity<ErrorResponse>
	 */
	@ExceptionHandler(value = { HttpClientErrorException.class, HttpServerErrorException.class })
	protected ResponseEntity<Object> handleHTTPException(HttpStatusCodeException httpStatusCodeException, WebRequest request) {
		// build error response
		log.info("Entered CustomExceptionHandler::handleHTTPException::");
		final String errorResponseText = httpStatusCodeException.getResponseBodyAsString();
		final ObjectMapper mapper = new ObjectMapper();
		// building default error response
		ErrorResponse errorResponse = new ErrorResponse();

		try {
			errorResponse = mapper.readValue(errorResponseText, ErrorResponse.class);
		} catch (IOException ioException) {
			log.error("cannot read error response [" + errorResponseText
					+ "] to convert to Object --> ErrorResponse, cause: " + ioException.getMessage(), ioException);
		}

		// build headers
		final HttpHeaders httpHeaders = getResponseHeaders(request);

		ErrorStatusCode statusCode = ErrorStatusCode.getStatusCode(httpStatusCodeException.getStatusCode().value());
		// global log
		log.error("HTTP Server error occured in controller, cause: " + httpStatusCodeException.getMessage(), httpStatusCodeException);

		// send error response
		return ResponseEntity.status(httpStatusCodeException.getStatusCode().value()).headers(httpHeaders).contentType(MediaType.APPLICATION_JSON).body((Object)errorResponse);
	}


   //@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error(ERROR + ex.getMessage(), ex);
		ErrorResponse errorResponse = ErrorResponse.instance(SeverityEnum.ERROR, ex, ex.getMessage(),
				ErrorStatusCode.METHOD_NOT_FOUND.getCode() + "", FAILED, true,
				String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()));

		return new ResponseEntity<Object>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	//@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("global error occured in controller, cause: " + ex.getMessage(), ex);
		ErrorResponse errorResponse = ErrorResponse.instance(SeverityEnum.ERROR, ex, ex.getMessage(),
				ErrorStatusCode.BAD_REQUEST.getCode() + "", FAILED, true,
				String.valueOf(HttpStatus.BAD_REQUEST.value()));

		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
