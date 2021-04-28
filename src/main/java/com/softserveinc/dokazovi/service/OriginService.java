package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.origin.OriginDTO;
import java.util.List;

public interface OriginService {
	List<OriginDTO> findAllOrigins();

	List<OriginDTO> findAllOriginsByUserId(Integer userId);
}
