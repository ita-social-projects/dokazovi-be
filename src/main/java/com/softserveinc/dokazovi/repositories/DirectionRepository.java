package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectionRepository extends JpaRepository<DirectionEntity, Integer> {

	@Query(nativeQuery = true,
	value = " UPDATE DIRECTIONS"
			+ " SET HAS_DOCTORS = TRUE"
			+ " WHERE DIRECTION_ID IN (SELECT DISTINCT DIRECTION_ID FROM DOCTORS_DIRECTIONS) ")
	@Modifying
	void updateDirectionsHasDoctorsStatus();

	@Query(nativeQuery = true,
	value = "UPDATE DIRECTIONS"
			+ " SET HAS_POSTS = TRUE"
			+ " WHERE DIRECTION_ID IN (SELECT DISTINCT DIRECTION_ID FROM POSTS_DIRECTIONS)")
	@Modifying
	void updateDirectionsHasPostsStatus();

	@Query(nativeQuery = true,
			value = "SELECT * FROM DIRECTIONS"
					+ " WHERE DIRECTION_ID IN (SELECT DISTINCT DIRECTION_ID FROM POSTS_DIRECTIONS "
					+ " WHERE POST_ID IN (SELECT DISTINCT POST_ID FROM POSTS "
					+ " WHERE AUTHOR_ID IN (:userId) ))")
	@Modifying
	List<DirectionEntity> findAllDirectionsByUserId(Integer userId);
}

