package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.region.RegionDTO;
import com.softserveinc.dokazovi.mapper.RegionMapper;
import com.softserveinc.dokazovi.repositories.RegionRepository;
import com.softserveinc.dokazovi.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

	private final RegionRepository regionRepository;
	private final RegionMapper regionMapper;

	@Override
	public List<RegionDTO> findAllRegions() {
		return regionRepository.findAll().stream()
				.map(regionMapper::toRegionDTO)
				.collect(Collectors.toList());
	}
}
