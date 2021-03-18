package com.softserveinc.dokazovi.validator;

import com.softserveinc.dokazovi.dto.source.SourceDTO;
import com.softserveinc.dokazovi.repositories.SourceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SourceExistsValidatorTest {

	@Mock
	private SourceRepository repository;

	@InjectMocks
	private SourceExistsValidator validator;

	@Test
	void isValid_WhenIdNotNull_ReturnTrue() {
		when(repository.existsById(anyInt())).thenReturn(true);
		SourceDTO dto = SourceDTO.builder()
				.id(1)
				.build();
		assertTrue(validator.isValid(dto, null));
	}

	@Test
	void isValid_WhenIdIsNull_ReturnFalse() {
		SourceDTO dto = SourceDTO.builder()
				.id(null)
				.build();
		assertFalse(validator.isValid(dto, null));
	}
}