package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.PostTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTypeRepository extends JpaRepository<PostTypeEntity, Integer> {
}
