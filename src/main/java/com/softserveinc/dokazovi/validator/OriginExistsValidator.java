package com.softserveinc.dokazovi.validator;

import com.softserveinc.dokazovi.annotations.OriginExists;
import com.softserveinc.dokazovi.dto.origin.OriginDTOForSavingPost;
import com.softserveinc.dokazovi.repositories.OriginRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class OriginExistsValidator implements ConstraintValidator<OriginExists, OriginDTOForSavingPost> {
	private final OriginRepository repository;

	@Override
	public boolean isValid(OriginDTOForSavingPost value, ConstraintValidatorContext context) {
		Integer id = value.getId();
		if (id == null) {
			return false;
		}
		return repository.existsById(id);
	}
}
