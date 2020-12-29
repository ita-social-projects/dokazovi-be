package com.softserveinc.dokazovi.error;

public class NotUniqueEntityException extends EntityException {

	public NotUniqueEntityException(Object o) {
		super(o + " is not unique");
	}
}
