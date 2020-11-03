package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<Source, Integer> {
}
