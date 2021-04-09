package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.RegionEntity;
import com.softserveinc.dokazovi.mapper.RegionMapper;
import com.softserveinc.dokazovi.repositories.RegionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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
	private RegionServiceImpl regionServiceMock;
	@InjectMocks
	private RegionServiceImpl regionService;

	@Test
	void findAllRegions() {
		List<RegionEntity> regions = List.of(new RegionEntity(), new RegionEntity());

		when(regionRepository.findAll()).thenReturn(regions);
		regionService.findAllRegions();

		verify(regionMapper, times(regions.size())).toRegionDTO(any(RegionEntity.class));
	}

	@Test
	void updateRegionsStatus() {

		regionService.updateRegionsStatus();

		verify(regionRepository, times(1)).updateRegionsStatus();
	}
}