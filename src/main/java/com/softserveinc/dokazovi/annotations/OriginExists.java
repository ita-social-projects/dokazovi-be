package com.softserveinc.dokazovi.annotations;

import com.softserveinc.dokazovi.validator.OriginExistsValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OriginExistsValidator.class)
public @interface OriginExists {
	String message() default "Origin doesn't exist";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
