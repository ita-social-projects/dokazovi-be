package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.Direction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository extends CrudRepository<Direction, Integer> {
}
