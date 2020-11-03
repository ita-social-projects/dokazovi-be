package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends CrudRepository<Region, Integer> {
}
