package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {
}
