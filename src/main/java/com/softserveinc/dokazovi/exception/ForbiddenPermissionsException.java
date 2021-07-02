package com.softserveinc.dokazovi.exception;

public class ForbiddenPermissionsException extends RuntimeException {
	private static final String MESSAGE = "Forbidden permission";

	public ForbiddenPermissionsException() {
		super(MESSAGE);
	}

	public ForbiddenPermissionsException(String msg) {
		super(msg);
	}
}
