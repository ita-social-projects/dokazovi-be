package com.softserveinc.dokazovi.pojo;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class BindingControllerAdvice {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}
}
