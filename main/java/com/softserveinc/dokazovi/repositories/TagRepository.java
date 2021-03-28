package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.TagEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {

	@Override
	@Cacheable("tags")
	List<TagEntity> findAll();
}
