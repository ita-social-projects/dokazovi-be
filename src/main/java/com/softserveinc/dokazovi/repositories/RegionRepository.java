package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The Region Repository is responsible for encapsulation a set of
 * Region objects stored in the database and operations that can be performed on them.
 */

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {

    /**
     * Updates the region's status.
     * If regions have at least one doctor, its status changes to "true".
     * In other cases - "false"
     */
    @Query(nativeQuery = true, value = "UPDATE REGIONS "
            + "SET USERS_PRESENT=TRUE "
            + "          WHERE REGION_ID IN (SELECT DISTINCT REGION_ID FROM CITIES"
            + "          WHERE CITY_ID IN (SELECT DISTINCT CITY_ID FROM INSTITUTIONS"
            + "          WHERE INSTITUTION_ID IN (SELECT DISTINCT INSTITUTION_ID FROM DOCTORS)))")
    @Modifying
    void updateRegionsStatus();

    RegionEntity findByCitiesId(Integer cityId);
}
