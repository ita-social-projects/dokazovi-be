package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
    Post getById(Integer id);
}
