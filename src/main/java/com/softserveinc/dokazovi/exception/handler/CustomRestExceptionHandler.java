package com.softserveinc.dokazovi.exception.handler;

import com.softserveinc.dokazovi.exception.DtoException;
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
		logger.info(ex.getClass().getName());

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

	@ExceptionHandler({DtoException.class})
	public ResponseEntity<Object> handleEntityException(final DtoException ex) {
		logger.info(ex.getClass().getName());
		final ApiError apiError = ApiError.builder()
				.status(HttpStatus.BAD_REQUEST)
				.errors(Collections.singletonList(ex.getLocalizedMessage()))
				.build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}
}
