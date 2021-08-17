package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.mapper.DirectionMapper;
import com.softserveinc.dokazovi.repositories.DirectionRepository;
import com.softserveinc.dokazovi.service.DirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The DirectionServiceImpl is responsible for doing any required logic
 *  with the direction data received by the Direction Controller.
 * It provides logic to operate on the data sent to and from the Direction repository.
 */

@Service
@RequiredArgsConstructor
public class DirectionServiceImpl implements DirectionService {

	private final DirectionRepository directionRepository;
	private final DirectionMapper directionMapper;

	/**
	 * Gets all directions.
	 *
	 * @return all found directions
	 */
	@Override
	public List<DirectionDTO> findAllDirections() {
		return directionRepository.findAll().stream()
				.map(directionMapper::toDirectionDTO)
				.collect(Collectors.toList());
	}

	/**
	 * Gets all directions by user id.
	 *
	 * @param userId received from Directions controller
	 * @return found directions by user id from directions repository
	 */
	@Override
	public List<DirectionDTO> findAllDirectionsByUserId(Integer userId) {
		return directionRepository.findAllDirectionsByUserId(userId).stream()
				.map(directionMapper::toDirectionDTO)
				.collect(Collectors.toList());
	}

	/**
	 *
	 * @param userId id of user
	 * @return list of directions from all user posts
	 */
	@Override
	public List<DirectionDTO> findAllDirectionsOfPostsByUserId(Integer userId) {
		return directionRepository.findAllDirectionsOfPostsByUserId(userId).stream()
				.map(directionMapper::toDirectionDTO)
				.collect(Collectors.toList());
	}

	/**
	 * Gets direction by id
	 *
	 * @param id direction id
	 * @return direction with appropriate id
	 */
	@Override
	public DirectionEntity getById(Integer id) {
		return directionRepository.findById(id).orElse(null);
	}

	/**
	 * Updates the directions status depending on the availability of doctors in it.
	 * Runs every four hours
	 */
	@Override
	@Transactional
	@Scheduled(cron = "0 0 */4 * * *")
	public void updateDirectionsHasDoctorsStatus() {
		directionRepository.updateDirectionsHasDoctorsStatus();
	}

	/**
	 * Updates the directions status depending on the availability of posts in it.
	 * Runs every four hours
	 */
	@Override
	@Transactional
	public void updateDirectionsHasPostsStatus(Set<Integer> directions) {
		directionRepository.updateDirectionsHasPostsStatus(directions);
	}

	@Override
	@Transactional
	public void updateDirectionsHasPostsStatusByEntities(Set<DirectionEntity> directions) {
		directionRepository.updateDirectionsHasPostsStatus(
				directions
					.stream()
					.map(DirectionEntity::getId)
					.collect(Collectors.toSet())
		);
	}

}
