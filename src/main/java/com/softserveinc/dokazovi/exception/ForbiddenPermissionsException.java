package com.softserveinc.dokazovi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenPermissionsException extends RuntimeException {
	private static final String MESSAGE = "Forbidden permission";

	public ForbiddenPermissionsException() {
		super(MESSAGE);
	}

	public ForbiddenPermissionsException(String msg) {
		super(msg);
	}
}
