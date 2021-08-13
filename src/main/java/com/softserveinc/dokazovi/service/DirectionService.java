package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;

import java.util.List;

public interface DirectionService {

	List<DirectionDTO> findAllDirections();

	List<DirectionDTO> findAllDirectionsByUserId(Integer userId);

	DirectionEntity getById(Integer id);

	void updateDirectionsHasDoctorsStatus();

	void updateDirectionsHasPostsStatus();
}
