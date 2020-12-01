package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.mapper.DirectionMapper;
import com.softserveinc.dokazovi.repositories.DirectionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DirectionServiceImplTest {

	@Mock
	private DirectionRepository directionRepository;
	@Mock
	private DirectionMapper directionMapper;
	@Mock
	private Pageable pageable;
	@InjectMocks
	private DirectionServiceImpl directionService;

	@Test
	void findAllDirections() {
		Page<DirectionEntity> directionEntityPage = new PageImpl<>(
				List.of(new DirectionEntity(), new DirectionEntity()));

		when(directionRepository.findAll(any(Pageable.class))).thenReturn(directionEntityPage);
		directionService.findAllDirections(pageable);

		verify(directionMapper, times(directionEntityPage.getNumberOfElements()))
				.toDirectionDTO(any(DirectionEntity.class));
	}
}