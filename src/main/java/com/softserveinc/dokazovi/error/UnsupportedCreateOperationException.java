package com.softserveinc.dokazovi.error;

public class UnsupportedCreateOperationException extends EntityException {

	public UnsupportedCreateOperationException(Object o) {
		super("It is impossible to create " + o + " in this case");
	}
}
