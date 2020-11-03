package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

	Page<Post> findAll(Pageable pageable);
}
