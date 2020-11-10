package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.post.ImportantPostDTO;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.mapper.ImportantPostMapper;
import com.softserveinc.dokazovi.repositories.PostRepository;
import com.softserveinc.dokazovi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final ImportantPostMapper importantPostMapper;

	@Override
	public Page<PostEntity> findAll(Pageable pageable) {
		return postRepository.findAll(pageable);
	}

	@Override
	public Page<ImportantPostDTO> findImportantPosts(Pageable pageable) {
		return postRepository.getImportantPosts(pageable).map(importantPostMapper::toImportantPostDTO);
	}
}
