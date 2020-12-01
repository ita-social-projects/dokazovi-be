package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.region.RegionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RegionService {

	Page<RegionDTO> findAllRegions(Pageable pageable);
}
