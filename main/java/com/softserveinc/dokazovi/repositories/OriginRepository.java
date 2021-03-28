package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.OriginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginRepository extends JpaRepository<OriginEntity, Integer> {
}
