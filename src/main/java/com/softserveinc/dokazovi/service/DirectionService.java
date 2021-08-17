package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;

import java.util.List;
import java.util.Set;

public interface DirectionService {

	List<DirectionDTO> findAllDirections();

	List<DirectionDTO> findAllDirectionsByUserId(Integer userId);

	List<DirectionDTO> findAllDirectionsOfPostsByUserId(Integer userId);

	DirectionEntity getById(Integer id);

	void updateDirectionsHasDoctorsStatus();

	void updateDirectionsHasPostsStatus(Set<Integer> directions);

	void updateDirectionsHasPostsStatusByEntities(Set<DirectionEntity> directions);

}
