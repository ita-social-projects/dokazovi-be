package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.region.RegionDTO;

import java.util.List;

public interface RegionService {
	//	same method at DirectionService, PostTypeService, OriginService
	List<RegionDTO> findAllRegions();

	public void updateRegionsStatus();
}
