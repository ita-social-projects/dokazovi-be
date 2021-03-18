package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.SourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<SourceEntity, Integer> {
}
