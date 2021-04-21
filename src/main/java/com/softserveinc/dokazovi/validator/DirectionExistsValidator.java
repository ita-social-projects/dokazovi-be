package com.softserveinc.dokazovi.validator;

import com.softserveinc.dokazovi.annotations.DirectionExists;
import com.softserveinc.dokazovi.dto.direction.DirectionDTOForSavingPost;
import com.softserveinc.dokazovi.repositories.DirectionRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class DirectionExistsValidator implements ConstraintValidator<DirectionExists, DirectionDTOForSavingPost> {

	private final DirectionRepository repository;

	@Override
	public boolean isValid(DirectionDTOForSavingPost value, ConstraintValidatorContext context) {
		Integer id = value.getId();
		if (id == null) {
			return false;
		}
		return repository.existsById(id);
	}
}
