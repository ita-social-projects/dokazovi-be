package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.DirectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository extends JpaRepository<DirectionEntity, Integer> {

	@Query(nativeQuery = true,
	value = " UPDATE DIRECTIONS"
			+ " SET HAS_DOCTORS = TRUE"
			+ " WHERE DIRECTION_ID IN (SELECT DIRECTION_ID FROM DOCTORS_DIRECTIONS) ")
	@Modifying
	void updateDirectionsStatus();
}

