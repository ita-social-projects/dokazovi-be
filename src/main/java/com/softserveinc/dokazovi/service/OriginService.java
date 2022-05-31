package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.origin.OriginDTO;
import java.util.List;

public interface OriginService {
//	same method at DirectionService, PostTypeService, RegionService
	List<OriginDTO> findAllOrigins();
}
