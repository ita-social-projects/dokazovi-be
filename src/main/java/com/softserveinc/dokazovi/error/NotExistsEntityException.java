package com.softserveinc.dokazovi.error;

public class NotExistsEntityException extends EntityException {

	public NotExistsEntityException(Object o) {
		super(o + " doesn't exist");
	}
}
