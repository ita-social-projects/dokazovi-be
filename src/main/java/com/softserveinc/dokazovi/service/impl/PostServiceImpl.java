package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.exception.InvalidIdDtoException;
import com.softserveinc.dokazovi.exception.ResourceNotFoundException;
import com.softserveinc.dokazovi.mapper.PostMapper;
import com.softserveinc.dokazovi.repositories.DirectionRepository;
import com.softserveinc.dokazovi.repositories.OriginRepository;
import com.softserveinc.dokazovi.repositories.PostRepository;
import com.softserveinc.dokazovi.repositories.PostTypeRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.RestAuthenticationEntryPoint;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.PostService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

	private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

	private final PostRepository postRepository;
	private final PostMapper postMapper;
	private final UserRepository userRepository;
	private final PostTypeRepository postTypeRepository;
	private final DirectionRepository directionRepository;
	private final OriginRepository originRepository;

	@Override
	public PostDTO findPostById(Integer postId) {
		return postMapper.toPostDTO(postRepository.findById(postId).orElse(null));
	}

	@Override
	public PostDTO saveFromUser(PostSaveFromUserDTO postDTO, UserPrincipal userPrincipal) {
		Integer postId = postDTO.getId();
		PostEntity mappedEntity;
		if (postId == null) {
			mappedEntity = postMapper.toPostEntity(postDTO);

		} else {
			mappedEntity = postRepository
					.findById(postId)
					.map(postEntity -> postMapper.updatePostEntityFromDTO(postDTO, postEntity))
					.orElseThrow(() -> new InvalidIdDtoException(postDTO));
		}

		UserEntity userEntity = userRepository.getOne(userPrincipal.getId());
		userEntity.setStatus(UserStatus.ACTIVE);
		mappedEntity.setAuthor(userEntity);
		mappedEntity.setImportant(false);
		mappedEntity.setStatus(PostStatus.MODERATION_FIRST_SIGN);
		mappedEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
		PostEntity savedEntity = postRepository.save(mappedEntity);
		return postMapper.toPostDTO(savedEntity);
	}

	@Override
	public Page<PostDTO> findAllByDirectionsAndByPostTypesAndByOrigins(Set<Integer> directionIds, Set<Integer> typeIds,
			Set<Integer> originIds, Pageable pageable) {
		if (directionIds == null && typeIds == null && originIds == null) {
			return postRepository.findAll(pageable)
					.map(postMapper::toPostDTO);
		}
		Set<Integer> directions = validateIdsValues(directionIds);
		Set<Integer> types = validateIdsValues(typeIds);
		Set<Integer> origins = validateIdsValues(originIds);
		try {
			return postRepository.findAllByDirectionsAndByPostTypesAndByOrigins(types, origins, directions, pageable)
					.map(postMapper::toPostDTO);
		} catch (Exception e) {
			logger.error(String.format("Fail with posts filter with params directionIds=%s, typeIds=%s, originIds=%s",
					directionIds, typeIds, originIds));
			throw new RuntimeException("Id does not exist");
		}
	}

	private Set<Integer> validateIdsValues(Set<Integer> ids) {
		return ids != null ? ids : new HashSet<>();
	}

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
	public Page<PostDTO> findAllByDirection(
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
