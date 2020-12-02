package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.region.RegionDTO;

import java.util.List;

public interface RegionService {

	List<RegionDTO> findAllRegions();
}
