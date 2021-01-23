package com.softserveinc.dokazovi.validator;

import com.softserveinc.dokazovi.annotations.PostExistsById;
import com.softserveinc.dokazovi.repositories.PostRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class PostExistsByIdValidator implements ConstraintValidator<PostExistsById, Integer> {

	private final PostRepository postRepository;

	@Override
	public boolean isValid(Integer postId, ConstraintValidatorContext context) {
		return postRepository.existsById(postId);
	}
}
