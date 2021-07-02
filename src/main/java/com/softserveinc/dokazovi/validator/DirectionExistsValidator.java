package com.softserveinc.dokazovi.validator;

import com.softserveinc.dokazovi.annotations.DirectionExists;
import com.softserveinc.dokazovi.dto.direction.DirectionDTOForSavingPost;
import com.softserveinc.dokazovi.repositories.DirectionRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * The DirectionExistsValidator is responsible for validation
 * a Direction DTO. It defines the logic to validate if the direction exists.
 */

@RequiredArgsConstructor
public class DirectionExistsValidator implements ConstraintValidator<DirectionExists, DirectionDTOForSavingPost> {

	private final DirectionRepository repository;

	/**
	 * Validates the Direction DTO for saving post.
	 * If direction id is zero, it returns "false".
	 *
	 * @param value DirectionDTOForSavingPost to validate
	 * @param context  context in which the constraint is evaluated
	 * @return true or false
	 */

	@Override
	public boolean isValid(DirectionDTOForSavingPost value, ConstraintValidatorContext context) {
		Integer id = value.getId();
		if (id == null) {
			return false;
		}
		return repository.existsById(id);
	}
}
