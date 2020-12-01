package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.RegionEntity;
import com.softserveinc.dokazovi.mapper.RegionMapper;
import com.softserveinc.dokazovi.repositories.RegionRepository;
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
class RegionServiceImplTest {

	@Mock
	private RegionRepository regionRepository;
	@Mock
	private RegionMapper regionMapper;
	@Mock
	private Pageable pageable;
	@InjectMocks
	private RegionServiceImpl regionService;

	@Test
	void findAllRegions() {
		Page<RegionEntity> regionEntityPage = new PageImpl<>(List.of(new RegionEntity(), new RegionEntity()));

		when(regionRepository.findAll(any(Pageable.class))).thenReturn(regionEntityPage);
		regionService.findAllRegions(pageable);

		verify(regionMapper, times(regionEntityPage.getNumberOfElements())).toRegionDTO(any(RegionEntity.class));
	}
}