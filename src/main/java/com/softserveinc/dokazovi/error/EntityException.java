package com.softserveinc.dokazovi.error;

public abstract class EntityException extends RuntimeException {

	public EntityException(String message) {
		super(message);
	}
}
