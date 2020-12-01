package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DirectionService {

	Page<DirectionDTO> findAllDirections(Pageable pageable);
}
