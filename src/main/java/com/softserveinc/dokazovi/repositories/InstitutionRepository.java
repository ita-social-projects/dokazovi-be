package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.InstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends JpaRepository<InstitutionEntity, Integer> {
}
