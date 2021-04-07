package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {
	@Query(nativeQuery = true, value = "UPDATE REGIONS\n"
			+ "SET USERS_PRESENT=TRUE\n"
			+ "          WHERE   REGION_ID IN (SELECT  REGION_ID FROM CITIES\n"
			+ "          WHERE  CITY_ID IN (SELECT  CITY_ID FROM INSTITUTIONS\n"
			+ "          WHERE  INSTITUTION_ID IN (SELECT  INSTITUTION_ID FROM DOCTORS)))")
	@Modifying
	void updateRegionsStatus();
}
