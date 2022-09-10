package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {

    List<CityEntity> findAllByRegionId(Integer id);
}