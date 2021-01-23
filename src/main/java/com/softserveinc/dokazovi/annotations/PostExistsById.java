package com.softserveinc.dokazovi.annotations;

import com.softserveinc.dokazovi.validator.PostExistsByIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PostExistsByIdValidator.class)
public @interface PostExistsById {
	String message() default "No posts found by id";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
