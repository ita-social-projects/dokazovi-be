package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.Source;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends CrudRepository<Source, Integer> {
}
