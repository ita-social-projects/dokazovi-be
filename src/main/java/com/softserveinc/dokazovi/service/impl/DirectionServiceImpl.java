package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.mapper.DirectionMapper;
import com.softserveinc.dokazovi.repositories.DirectionRepository;
import com.softserveinc.dokazovi.service.DirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DirectionServiceImpl implements DirectionService {

	private final DirectionRepository directionRepository;
	private final DirectionMapper directionMapper;

	@Override
	public List<DirectionDTO> findAllDirections() {
		return directionRepository.findAll().stream()
				.map(directionMapper::toDirectionDTO)
				.collect(Collectors.toList());
	}

	public boolean exists(DirectionEntity directionEntity) {
		return directionRepository.exists(Example.of(directionEntity));
	}
}
