package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

	Page<PostEntity> findAll(Pageable pageable);
}
