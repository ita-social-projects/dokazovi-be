package com.softserveinc.dokazovi.error;

public abstract class EntityException extends RuntimeException {

	protected EntityException(String message) {
		super(message);
	}
}
