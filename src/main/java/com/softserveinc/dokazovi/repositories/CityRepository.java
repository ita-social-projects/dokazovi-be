package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Integer> {

    List<CityEntity> findAllByRegionId(Integer regionId);
}
