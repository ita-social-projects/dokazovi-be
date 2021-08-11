package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.foreignexpert.ForeignExpertDTO;
import com.softserveinc.dokazovi.dto.foreignexpert.ForeignExpertSearchResultDTO;
import com.softserveinc.dokazovi.entity.ForeignExpertEntity;
import com.softserveinc.dokazovi.exception.EntityNotFoundException;
import com.softserveinc.dokazovi.exception.ForbiddenPermissionsException;
import com.softserveinc.dokazovi.mapper.ForeignExpertMapper;
import com.softserveinc.dokazovi.repositories.ForeignExpertRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.ForeignExpertService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForeignExpertServiceImpl implements ForeignExpertService {

	private static final Logger logger = LoggerFactory.getLogger(ForeignExpertServiceImpl.class);

	private final ForeignExpertRepository foreignExpertRepository;
	private final ForeignExpertMapper foreignExpertMapper;

	@Override
	public ForeignExpertDTO save(ForeignExpertSearchResultDTO saveDTO, UserPrincipal userPrincipal) {
		if (userPrincipal.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("SAVE_FOREIGN_EXPERT"))) {
			throw new ForbiddenPermissionsException();
		}

		ForeignExpertEntity entity = getEntityFromSaveDTO(saveDTO);
		entity = foreignExpertRepository.save(entity);
		return foreignExpertMapper.toForeignExpertDTO(entity);
	}

	@Override
	public Page<ForeignExpertSearchResultDTO> search(String query, Pageable pageable) {
		return foreignExpertRepository.searchByFullName(query, pageable)
				.map(foreignExpertMapper::toForeignExpertSearchResultDTO);
	}

	private ForeignExpertEntity getEntityFromSaveDTO(ForeignExpertSearchResultDTO saveDTO) {
		Integer id = saveDTO.getId();
		ForeignExpertEntity entity;
		if (id == null) {
			entity = foreignExpertMapper.toForeignExpertEntity(saveDTO);
		} else {
			ForeignExpertEntity byId = foreignExpertRepository.findById(id)
					.orElseThrow(EntityNotFoundException::new);
			entity = foreignExpertMapper.updateForeignExpertEntityFromDTO(
					saveDTO,
					byId
			);
		}

		return entity;
	}

}
