package com.softserveinc.dokazovi.handler;

import com.softserveinc.dokazovi.error.EntityException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	// 400

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.warn(ex.getClass().getName());

		List<String> errors = ex.getBindingResult().getFieldErrors()
				.stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage())
				.collect(Collectors.toList());

		final ApiError apiError = ApiError.builder()
				.status(HttpStatus.BAD_REQUEST)
				.errors(errors)
				.build();
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}

	@ExceptionHandler({EntityException.class})
	public ResponseEntity<Object> handleEntityException(final EntityException ex) {
		logger.warn(ex.getClass().getName() + ": [" + ex.getMessage() + "]");
		final ApiError apiError = ApiError.builder()
				.status(HttpStatus.BAD_REQUEST)
				.errors(Collections.singletonList(ex.getLocalizedMessage()))
				.build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// 500

	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
		logger.info(ex.getClass().getName());
		logger.error("error", ex);
		//
		final ApiError apiError = ApiError.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.errors(Collections.singletonList(ex.getLocalizedMessage()))
				.build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}
}