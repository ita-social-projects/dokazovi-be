package com.softserveinc.dokazovi.validator;

import com.softserveinc.dokazovi.annotations.TagExists;
import com.softserveinc.dokazovi.dto.tag.TagDTO;
import com.softserveinc.dokazovi.repositories.TagRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class TagExistsValidator implements ConstraintValidator<TagExists, TagDTO> {

    private final TagRepository repository;

    @Override
    public boolean isValid(TagDTO value, ConstraintValidatorContext context) {
        Integer id = value.getId();
        if (id == null) {
            return false;
        }
        return repository.existsById(id);
    }
}
