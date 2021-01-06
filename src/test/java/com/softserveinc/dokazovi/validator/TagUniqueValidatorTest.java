package com.softserveinc.dokazovi.validator;

import com.softserveinc.dokazovi.repositories.TagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagUniqueValidatorTest {

	@Mock
	private TagRepository tagRepository;

	@InjectMocks
	private TagUniqueValidator validator;

	@Test
	void isValid_WhenStringUnique_ReturnTrue() {
		when(tagRepository.exists(any())).thenReturn(false);
		assertTrue(validator.isValid("value", null));
	}

	@Test
	void isValid_WhenStringNotUnique_ReturnFalse() {
		when(tagRepository.exists(any())).thenReturn(true);
		assertFalse(validator.isValid("value", null));
	}
}