package com.lazymarks.platform.api;

import com.lazymarks.platform.api.model.GenericResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(BadCredentialsException.class)
	protected ResponseEntity<Object> handleIllegalArgumentException(BadCredentialsException ex, WebRequest request) {
		ProblemDetail body = createProblemDetail(ex, HttpStatus.UNAUTHORIZED, ex.getMessage(), null, null, request);
		return createResponseEntity(body, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Object> handleCustomerAlreadyExistsException(UsernameNotFoundException ex, WebRequest request) {
		return createResponseEntity(GenericResponse.error(ex.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleBadRequestException(IllegalArgumentException ex, WebRequest request) {
		return createResponseEntity(GenericResponse.error(ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

}
