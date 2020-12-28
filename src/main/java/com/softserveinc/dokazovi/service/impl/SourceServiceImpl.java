package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.SourceEntity;
import com.softserveinc.dokazovi.repositories.SourceRepository;
import com.softserveinc.dokazovi.service.SourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SourceServiceImpl implements SourceService {

	private final SourceRepository sourceRepository;

	@Override
	public boolean exists(SourceEntity sourceEntity) {
		return sourceRepository.exists(Example.of(sourceEntity));
	}
}
