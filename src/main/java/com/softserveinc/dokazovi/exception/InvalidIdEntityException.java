package com.softserveinc.dokazovi.exception;

public class InvalidIdEntityException extends EntityException {

	public InvalidIdEntityException(Object o) {
		super(o + " has invalid id");
	}
}
