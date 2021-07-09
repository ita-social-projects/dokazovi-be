package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.PlatformInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformInformationRepository extends JpaRepository<PlatformInformationEntity, Integer> {

}
