package com.softserveinc.dokazovi.validator;

import com.softserveinc.dokazovi.annotations.OriginExists;
import com.softserveinc.dokazovi.dto.origin.OriginDTO;
import com.softserveinc.dokazovi.repositories.OriginRepository;
import lombok.RequiredArgsConstructor;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class OriginExistsValidator implements ConstraintValidator<OriginExists, OriginDTO> {
	private final OriginRepository repository;

	@Override
	public boolean isValid(OriginDTO value, ConstraintValidatorContext context) {
		Integer id = value.getId();
		if (id == null) {
			return false;
		}
		return repository.existsById(id);
	}
}
