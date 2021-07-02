package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.direction.DirectionDTO;

import java.util.List;

public interface DirectionService {

	List<DirectionDTO> findAllDirections();

	List<DirectionDTO> findAllDirectionsByUserId(Integer userId);

	void updateDirectionsHasDoctorsStatus();

	void updateDirectionsHasPostsStatus();
}
