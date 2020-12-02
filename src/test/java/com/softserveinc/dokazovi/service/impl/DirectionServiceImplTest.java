package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.mapper.DirectionMapper;
import com.softserveinc.dokazovi.repositories.DirectionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
	@InjectMocks
	private DirectionServiceImpl directionService;

	@Test
	void findAllDirections() {
		List<DirectionEntity> directions = List.of(new DirectionEntity(), new DirectionEntity());

		when(directionRepository.findAll()).thenReturn(directions);
		directionService.findAllDirections();

		verify(directionMapper, times(directions.size())).toDirectionDTO(any(DirectionEntity.class));
	}
}