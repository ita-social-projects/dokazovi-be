package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.origin.OriginDTO;
import com.softserveinc.dokazovi.mapper.OriginMapper;
import com.softserveinc.dokazovi.repositories.OriginRepository;
import com.softserveinc.dokazovi.service.OriginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OriginServiceImpl implements OriginService {

	private final OriginRepository originRepository;
	private final OriginMapper originMapper;

	@Override
	public List<OriginDTO> findAllOrigins() {
		return originRepository.findAll().stream()
				.map(originMapper::toOriginDTO)
				.collect(Collectors.toList());
	}
}