package com.softserveinc.dokazovi.annotations;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
		@ApiImplicitParam(name = "page", dataType = "java.lang.Integer", paramType = "query", defaultValue = "0",
				value = "Page index you want to retrieve [0..N]. "
						+ "If page index is less than 0 or not specified then default value is used!"),
		@ApiImplicitParam(name = "size", dataType = "java.lang.Integer", paramType = "query", defaultValue = "10",
				value = "Number of records per page. "
						+ "If size is less than 1 or not specified then default value is used!"),
		@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "java.lang.String", paramType = "query",
				value = "Properties that should be sorted by in the format property,property(,ASC|DESC)." +
						"Default sort direction is ascending. \nUse multiple sort parameters if you want to " +
						"switch directions, e.g. ?sort=firstname&sort=lastname,asc.")
})
public @interface ApiPageable {

}