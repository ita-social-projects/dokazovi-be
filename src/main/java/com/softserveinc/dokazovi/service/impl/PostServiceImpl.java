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
			Integer directionId, Set<Integer> typeId, Set<Integer> tagId, PostStatus postStatus, Pageable pageable) {
		if (typeId == null && tagId == null) {
			return postRepository.findAllByMainDirectionIdAndStatus(directionId, postStatus, pageable)
					.map(postMapper::toPostDTO);
		} else if (typeId == null) {
			return postRepository.findAllByMainDirectionIdAndTagsIdInAndStatus(directionId, tagId, postStatus, pageable)
					.map(postMapper::toPostDTO);
		} else if (tagId == null) {
			return postRepository.findAllByMainDirectionIdAndTypeIdInAndStatus(
					directionId, typeId, postStatus, pageable)
					.map(postMapper::toPostDTO);
		}
		return postRepository.findAllByMainDirectionIdAndTypeIdInAndTagsIdInAndStatus(
				directionId, typeId, tagId, postStatus, pageable)
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
