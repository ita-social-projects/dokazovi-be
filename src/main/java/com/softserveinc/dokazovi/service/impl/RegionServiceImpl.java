package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.region.RegionDTO;
import com.softserveinc.dokazovi.mapper.RegionMapper;
import com.softserveinc.dokazovi.repositories.RegionRepository;
import com.softserveinc.dokazovi.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

	private final RegionRepository regionRepository;
	private final RegionMapper regionMapper;

	@Override
	public Page<RegionDTO> findAllRegions(Pageable pageable) {
		return regionRepository.findAll(pageable)
				.map(regionMapper::toRegionDTO);
	}
}
