package com.softserveinc.dokazovi.error;

public class InvalidIdEntityException extends EntityException {

	public InvalidIdEntityException(Object o) {
		super(o + " has invalid id");
	}
}
