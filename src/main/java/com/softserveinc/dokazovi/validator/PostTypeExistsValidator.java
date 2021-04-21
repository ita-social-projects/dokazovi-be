package com.softserveinc.dokazovi.validator;

import com.softserveinc.dokazovi.annotations.PostTypeExists;
import com.softserveinc.dokazovi.dto.post.PostTypeIdOnlyDTO;
import com.softserveinc.dokazovi.repositories.PostTypeRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class PostTypeExistsValidator implements ConstraintValidator<PostTypeExists, PostTypeIdOnlyDTO> {

	private final PostTypeRepository repository;

	@Override
	public boolean isValid(PostTypeIdOnlyDTO value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		Integer id = value.getId();
		if (id == null) {
			return false;
		}
		return repository.existsById(id);
	}
}
