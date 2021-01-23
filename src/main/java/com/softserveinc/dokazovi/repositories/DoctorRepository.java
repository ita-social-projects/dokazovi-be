package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DoctorRepository extends JpaRepository<DoctorEntity, Integer> {

}
