package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.mapper.DirectionMapper;
import com.softserveinc.dokazovi.repositories.DirectionRepository;
import com.softserveinc.dokazovi.service.DirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectionServiceImpl implements DirectionService {

	private final DirectionRepository directionRepository;
	private final DirectionMapper directionMapper;

	@Override
	public Page<DirectionDTO> findAllDirections(Pageable pageable) {
		return directionRepository.findAll(pageable)
				.map(directionMapper::toDirectionDTO);
	}
}
