package com.softserveinc.dokazovi.error;

public class NotExistsEntityException extends EntityException {

	public NotExistsEntityException(Object o) {
		super(o + " is invalid");
	}
}
