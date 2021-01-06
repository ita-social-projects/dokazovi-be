package com.softserveinc.dokazovi.validator;

import com.softserveinc.dokazovi.annotations.SourceExists;
import com.softserveinc.dokazovi.dto.source.SourceDTO;
import com.softserveinc.dokazovi.repositories.SourceRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class SourceExistsValidator implements ConstraintValidator<SourceExists, SourceDTO> {

	private final SourceRepository repository;

	@Override
	public boolean isValid(SourceDTO value, ConstraintValidatorContext context) {
		Integer id = value.getId();
		if (id == null) {
			return false;
		}
		return repository.existsById(id);
	}
}
