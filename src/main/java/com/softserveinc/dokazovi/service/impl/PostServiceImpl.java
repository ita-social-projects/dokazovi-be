package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.mapper.PostMapper;
import com.softserveinc.dokazovi.repositories.PostRepository;
import com.softserveinc.dokazovi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final PostMapper postMapper;

	@Override
	public Page<PostDTO> findAllByStatus(PostStatus postStatus, Pageable pageable) {
		return postRepository.findAllByStatus(postStatus, pageable)
				.map(postMapper::toPostDTO);
	}

	@Override
	public Page<PostDTO> findImportantPosts(Pageable pageable) {
		return postRepository.findAllByImportantIsTrueAndStatus(PostStatus.PUBLISHED, pageable)
				.map(postMapper::toPostDTO);
	}

	@Override
	public Page<PostDTO> findAllByMainDirection(
			Integer directionId, Integer typeId, Set<Integer> tags, Pageable pageable) {
		if (typeId == null && tags == null) {
			return postRepository.findAllByMainDirectionId(directionId, pageable)
					.map(postMapper::toPostDTO);
		} else if (typeId == null) {
			return postRepository.findAllByMainDirectionIdAndTagsIdIn(directionId, tags, pageable)
					.map(postMapper::toPostDTO);
		} else if (tags == null) {
			return postRepository.findAllByMainDirectionIdAndTypeId(directionId, typeId, pageable)
					.map(postMapper::toPostDTO);
		}
		return postRepository.findAllByMainDirectionIdAndTypeIdAndTagsIdIn(directionId, typeId, tags, pageable)
				.map(postMapper::toPostDTO);
	}

}
