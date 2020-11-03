package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {
}
