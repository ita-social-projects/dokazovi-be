package com.softserveinc.dokazovi.validator;

import com.softserveinc.dokazovi.annotations.PostTypeExists;
import com.softserveinc.dokazovi.dto.post.PostTypeDTO;
import com.softserveinc.dokazovi.repositories.PostTypeRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class PostTypeExistsValidator implements ConstraintValidator<PostTypeExists, PostTypeDTO> {

	private final PostTypeRepository repository;

	@Override
	public boolean isValid(PostTypeDTO value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		Integer id = value.getId();
		return id != null && repository.existsById(id);
	}
}
