package com.softserveinc.dokazovi.validator;

import com.softserveinc.dokazovi.annotations.DirectionExists;
import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.repositories.DirectionRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class DirectionExistsValidator implements ConstraintValidator<DirectionExists, DirectionDTO> {

	private final DirectionRepository repository;

	@Override
	public boolean isValid(DirectionDTO value, ConstraintValidatorContext context) {
		Integer id = value.getId();
		return id != null && repository.existsById(id);
	}
}
