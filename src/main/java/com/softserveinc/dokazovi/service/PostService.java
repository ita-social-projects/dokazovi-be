package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

	Page<PostDTO> findAll(Pageable pageable);

	Page<PostDTO> findAllByStatus(PostStatus postStatus, Pageable pageable);
}
