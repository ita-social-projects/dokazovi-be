package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.dto.post.PostLatestByDirectionFilterDTO;
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
			PostLatestByDirectionFilterDTO postParamsDTO, PostStatus postStatus, Pageable pageable) {
		Integer direction = postParamsDTO.getDirection();
		Integer typeId = postParamsDTO.getType();
		Set<Integer> tags = postParamsDTO.getTags();
		if (typeId == null && tags == null) {
			return postRepository.findAllByMainDirectionIdAndStatus(direction, postStatus, pageable)
					.map(postMapper::toPostDTO);
		} else if (typeId == null) {
			return postRepository.findAllByMainDirectionIdAndTagsIdInAndStatus(direction, tags, postStatus, pageable)
					.map(postMapper::toPostDTO);
		} else if (tags == null) {
			return postRepository.findAllByMainDirectionIdAndTypeIdAndStatus(direction, typeId, postStatus, pageable)
					.map(postMapper::toPostDTO);
		}
		return postRepository.findAllByMainDirectionIdAndTypeIdAndTagsIdInAndStatus(
				direction, typeId, tags, postStatus, pageable)
				.map(postMapper::toPostDTO);
	}

}
