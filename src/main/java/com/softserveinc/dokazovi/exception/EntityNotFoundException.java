package com.softserveinc.dokazovi.exception;

public class EntityNotFoundException extends RuntimeException {
	final private static String message = "Entity not found";

	public EntityNotFoundException() {
		super(message);
	}

	public EntityNotFoundException(String msg) {
		super(msg);
	}
}
