package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.Post;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {

}
