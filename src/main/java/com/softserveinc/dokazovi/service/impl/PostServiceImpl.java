package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.post.ImportantPostDTO;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.mapper.PostMapper;
import com.softserveinc.dokazovi.repositories.PostRepository;
import com.softserveinc.dokazovi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final PostMapper postMapper;

	@Autowired
	public PostServiceImpl(PostRepository postRepository,
			PostMapper postMapper) {
		this.postRepository = postRepository;
		this.postMapper = postMapper;
	}

	@Override
	public Page<PostEntity> findAll(Pageable pageable) {
		return postRepository.findAll(pageable);
	}

	@Override
	public Page<ImportantPostDTO> findImportantPosts(Pageable pageable) {
		return postRepository.findAllByImportantIsTrueAndStatus(PostStatus.PUBLISHED, pageable)
				.map(postMapper::toImportantPostDTO);
	}
}
