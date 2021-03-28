package com.softserveinc.dokazovi.annotations;

import com.softserveinc.dokazovi.validator.PostTypeExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PostTypeExistsValidator.class)
public @interface PostTypeExists {
	String message() default "Post doesn't exist";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
