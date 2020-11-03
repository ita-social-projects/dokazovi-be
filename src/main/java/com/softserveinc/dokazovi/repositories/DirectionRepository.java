package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Integer> {
}
