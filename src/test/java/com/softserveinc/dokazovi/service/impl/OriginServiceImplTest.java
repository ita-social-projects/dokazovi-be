package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.OriginEntity;
import com.softserveinc.dokazovi.mapper.OriginMapper;
import com.softserveinc.dokazovi.repositories.OriginRepository;
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
class OriginServiceImplTest {
	@Mock
	private OriginRepository originRepository;
	@Mock
	private OriginMapper originMapper;
	@InjectMocks
	private OriginServiceImpl originService;

	@Test
	void findAllOrigins() {
		List<OriginEntity> origins = List.of(new OriginEntity(), new OriginEntity());

		when(originRepository.findAll()).thenReturn(origins);
		originService.findAllOrigins();

		verify(originMapper, times(origins.size())).toOriginDTO(any(OriginEntity.class));
	}
}
