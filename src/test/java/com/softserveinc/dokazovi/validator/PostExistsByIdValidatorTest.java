package com.softserveinc.dokazovi.validator;

import com.softserveinc.dokazovi.repositories.PostRepository;
import com.softserveinc.dokazovi.repositories.TagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostExistsByIdValidatorTest {

	@Mock
	private PostRepository postRepository;

	@InjectMocks
	private PostExistsByIdValidator validator;

	@Test
	void isValid_PostNotExists_ReturnFalse() {
		when(postRepository.existsById(any())).thenReturn(false);
		assertFalse(validator.isValid(1, null));
	}

	@Test
	void isValid_PostExists_ReturnTrue() {
		when(postRepository.existsById(any())).thenReturn(true);
		assertTrue(validator.isValid(1, null));
	}
}