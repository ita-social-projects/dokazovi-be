package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.PostType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTypeRepository extends CrudRepository<PostType, Integer> {
}
