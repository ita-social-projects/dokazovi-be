package com.softserveinc.dokazovi.pojo;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * The BindingControllerAdvice is responsible for configuring the data binder globally for the whole application.
 */

@ControllerAdvice
public class BindingControllerAdvice {

	/**
	 * Accepts the binder as an input.
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}
}
