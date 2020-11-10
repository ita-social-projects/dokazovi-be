package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.repositories.PostRepository;
import com.softserveinc.dokazovi.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;

	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public Page<PostEntity> findAll(Pageable pageable) {
		return postRepository.findAll(pageable);
	}

	@Override
	public Page<PostEntity> findImportantPosts(Pageable pageable) {
		// TODO: create method and SELECT query form important = true
		return null;
	}
}
