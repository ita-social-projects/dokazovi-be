package com.softserveinc.dokazovi.validator;

import com.softserveinc.dokazovi.annotations.TagUnique;
import com.softserveinc.dokazovi.entity.TagEntity;
import com.softserveinc.dokazovi.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@RequiredArgsConstructor
public class TagUniqueValidator implements ConstraintValidator<TagUnique, String> {

    private final TagRepository repository;
    private final ExampleMatcher modelMatcher = ExampleMatcher.matching()
            .withIgnorePaths("id")
            .withMatcher("tag", ignoreCase());

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        TagEntity tagEntity = TagEntity.builder()
                .tag(value)
                .build();
        return !repository.exists(Example.of(tagEntity, modelMatcher));
    }
}
