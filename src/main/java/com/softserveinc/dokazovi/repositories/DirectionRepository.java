package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.DirectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository extends JpaRepository<DirectionEntity, Integer> {
}
