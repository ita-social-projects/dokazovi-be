package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;
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
			Integer directionId, Set<Integer> typeId, Set<Integer> tagId, PostStatus postStatus, Pageable pageable) {
		DirectionEntity direction = DirectionEntity.builder()
				.id(directionId)
				.build();
		if (typeId == null && tagId == null) {
			return postRepository.findAllByDirectionsContainsAndStatus(direction, postStatus, pageable)
					.map(postMapper::toPostDTO);
		} else if (typeId == null) {
			return postRepository.findAllByDirectionsContainsAndTagsIdInAndStatus(
					direction, tagId, postStatus, pageable)
					.map(postMapper::toPostDTO);
		} else if (tagId == null) {
			return postRepository.findAllByDirectionsContainsAndTypeIdInAndStatus(
					direction, typeId, postStatus, pageable)
					.map(postMapper::toPostDTO);
		}
		return postRepository.findAllByDirectionsContainsAndTypeIdInAndTagsIdInAndStatus(
				direction, typeId, tagId, postStatus, pageable)
				.map(postMapper::toPostDTO);
	}

	@Override
	public Page<PostDTO> findAllByExpert(
			Integer expertId, Set<Integer> typeId, PostStatus postStatus, Pageable pageable) {
		if (typeId == null) {
			return postRepository.findAllByAuthorIdAndStatus(expertId, postStatus, pageable)
					.map(postMapper::toPostDTO);
		}
		return postRepository.findAllByAuthorIdAndTypeIdInAndStatus(expertId, typeId, postStatus, pageable)
				.map(postMapper::toPostDTO);
	}
}
