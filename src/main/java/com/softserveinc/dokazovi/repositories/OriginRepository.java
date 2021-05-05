package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.OriginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Origin Repository is responsible for encapsulation a set of
 * Origin objects stored in the database and operations that can be performed on them.
 */
@Repository
public interface OriginRepository extends JpaRepository<OriginEntity, Integer> {
}
