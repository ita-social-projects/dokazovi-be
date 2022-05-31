package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;

import java.util.List;
import java.util.Set;

public interface DirectionService {
	// same method at OriginService, PostTypeService, RegionService
	List<DirectionDTO> findAllDirections();
	// how can we put in on top
	List<DirectionDTO> findAllDirectionsByUserId(Integer userId);

	List<DirectionDTO> findAllDirectionsByDoctorId(Integer userId);

	List<DirectionDTO> findAllDirectionsOfPostsByUserId(Integer userId);

	List<DirectionDTO> findAllDirectionsOfPostsByDoctorId(Integer userId);
	//same method at Interfaces except PlatformInformationService,
	DirectionEntity getById(Integer id);

	void updateDirectionsHasDoctorsStatus();

	void updateDirectionsHasPostsStatus(Set<Integer> directions);

	void updateDirectionsHasPostsStatusByEntities(Set<DirectionEntity> directions);

}
