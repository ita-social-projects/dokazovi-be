package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {}