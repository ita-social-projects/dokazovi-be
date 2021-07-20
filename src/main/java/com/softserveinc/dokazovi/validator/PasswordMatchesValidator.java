package com.softserveinc.dokazovi.validator;

import com.softserveinc.dokazovi.annotations.PasswordMatches;
import com.softserveinc.dokazovi.dto.user.UserPasswordDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	private final String PASSWORD_PATTERN = "[^=]{8,24}";

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		UserPasswordDTO passwordDTO = (UserPasswordDTO) value;
		return passwordDTO.getNewPassword().equals(passwordDTO.getMatchPassword())
				&& Pattern.compile(PASSWORD_PATTERN).matcher(passwordDTO.getNewPassword()).matches()
				&& !passwordDTO.getToken().isEmpty();
	}
}
