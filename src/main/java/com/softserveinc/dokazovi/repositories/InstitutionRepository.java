package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.Institution;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends CrudRepository<Institution, Integer> {
}
