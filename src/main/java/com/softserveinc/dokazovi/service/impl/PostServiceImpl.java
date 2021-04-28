package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.exception.EntityNotFoundException;
import com.softserveinc.dokazovi.exception.ForbiddenPermissionsException;
import com.softserveinc.dokazovi.mapper.PostMapper;
import com.softserveinc.dokazovi.repositories.DirectionRepository;
import com.softserveinc.dokazovi.repositories.OriginRepository;
import com.softserveinc.dokazovi.repositories.PostRepository;
import com.softserveinc.dokazovi.repositories.PostTypeRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
		PostEntity mappedEntity = getPostEntityFromPostDTO(postDTO);

		UserEntity userEntity = userRepository.getOne(userPrincipal.getId());
		mappedEntity.setImportant(false);

		mappedEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

		if (userEntity.getId().equals(postDTO.getAuthorId()) && userPrincipal.getAuthorities().stream()
				.anyMatch(grantedAuthority -> grantedAuthority
						.getAuthority().equals("SAVE_OWN_PUBLICATION"))) {
			mappedEntity.setStatus(PostStatus.MODERATION_FIRST_SIGN);
			mappedEntity.setAuthor(userEntity);
			return postMapper.toPostDTO(postRepository.save(mappedEntity));
		}

		if (!userEntity.getId().equals(postDTO.getAuthorId()) && userPrincipal.getAuthorities()
				.stream().anyMatch(grantedAuthority ->
						grantedAuthority.getAuthority().equals("SAVE_PUBLICATION"))) {
			mappedEntity.setStatus(PostStatus.PUBLISHED);
			mappedEntity.setAuthor(userRepository.getOne(postDTO.getAuthorId()));
			return postMapper.toPostDTO(postRepository.save(mappedEntity));
		}

		if (!userEntity.getId().equals(postDTO.getAuthorId()) || userPrincipal.getAuthorities().stream()
				.noneMatch(grantedAuthority ->
						grantedAuthority.getAuthority().equals("SAVE_OWN_PUBLICATION"))
				&& userPrincipal.getAuthorities().stream().noneMatch(grantedAuthority ->
				grantedAuthority.getAuthority().equals("SAVE_PUBLICATION"))) {
			throw new ForbiddenPermissionsException();
		}
		directionRepository.updateDirectionsHasPostsStatus();
		return postMapper.toPostDTO(mappedEntity);
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
			throw new EntityNotFoundException("Id does not exist");
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

	@Override
	public Page<PostDTO> findPostsByAuthorIdAndDirections(
			Pageable pageable, Integer authorId, Set<Integer> directions) {

		return postRepository.findPostsByAuthorIdAndDirections(pageable, authorId, directions)
				.map(postMapper::toPostDTO);
	}

	@Override
	public Boolean archivePostById(UserPrincipal userPrincipal, Integer postId)
			throws EntityNotFoundException {

		PostEntity mappedEntity = postRepository
				.findById(postId)
				.orElseThrow(() -> new EntityNotFoundException(String.format("Post with %s not found", postId)));

		Integer userId = userPrincipal.getId();
		Integer authorId = mappedEntity.getAuthor().getId();

		if (userId.equals(authorId) && userPrincipal.getAuthorities().stream().anyMatch(grantedAuthority ->
				grantedAuthority.getAuthority().equals("DELETE_OWN_POST"))) {
			mappedEntity.setStatus(PostStatus.ARCHIVED);
			mappedEntity.setModifiedAt(Timestamp.valueOf(LocalDateTime.now()));
			postRepository.save(mappedEntity);
		}

		if (!userId.equals(authorId) && userPrincipal.getAuthorities().stream().anyMatch(grantedAuthority ->
				grantedAuthority.getAuthority().equals("DELETE_POST"))) {
			mappedEntity.setStatus(PostStatus.ARCHIVED);
			mappedEntity.setModifiedAt(Timestamp.valueOf(LocalDateTime.now()));
			postRepository.save(mappedEntity);
		}

		if ((!userId.equals(authorId) || userPrincipal.getAuthorities().stream().noneMatch(grantedAuthority ->
				grantedAuthority.getAuthority().equals("DELETE_OWN_POST")))
				&& userPrincipal.getAuthorities().stream().noneMatch(grantedAuthority ->
				grantedAuthority.getAuthority().equals("DELETE_POST"))) {
			throw new ForbiddenPermissionsException();
		}
		directionRepository.updateDirectionsHasPostsStatus();
		return true;
	}

	@Override
	@Transactional
	public Boolean updatePostById(UserPrincipal userPrincipal, PostSaveFromUserDTO postDTO)
			throws EntityNotFoundException {

		PostEntity mappedEntity = getPostEntityFromPostDTO(postDTO);
		mappedEntity.setModifiedAt(Timestamp.valueOf(LocalDateTime.now()));

		Integer userId = userPrincipal.getId();
		Integer authorId = mappedEntity.getAuthor().getId();

		if (userId.equals(authorId) && userPrincipal.getAuthorities().stream().anyMatch(grantedAuthority ->
				grantedAuthority.getAuthority().equals("UPDATE_OWN_POST"))) {
			mappedEntity.setStatus(PostStatus.MODERATION_FIRST_SIGN);
			postRepository.save(mappedEntity);
		}

		if (!userId.equals(authorId) && userPrincipal.getAuthorities().stream().anyMatch(grantedAuthority ->
				grantedAuthority.getAuthority().equals("UPDATE_POST"))) {
			mappedEntity.setStatus(PostStatus.PUBLISHED);
			mappedEntity.setAuthor(userRepository.getOne(postDTO.getAuthorId()));
			postRepository.save(mappedEntity);
		}

		if ((!userId.equals(authorId) || userPrincipal.getAuthorities().stream().noneMatch(grantedAuthority ->
				grantedAuthority.getAuthority().equals("UPDATE_OWN_POST")))
				&& userPrincipal.getAuthorities().stream().noneMatch(grantedAuthority ->
				grantedAuthority.getAuthority().equals("UPDATE_POST"))) {
			throw new ForbiddenPermissionsException();
		}
		directionRepository.updateDirectionsHasPostsStatus();
		return true;
	}

	private PostEntity getPostEntityFromPostDTO(PostSaveFromUserDTO postDTO) {
		Integer postId = postDTO.getId();
		PostEntity mappedEntity;
		if (postId == null) {
			mappedEntity = postMapper.toPostEntity(postDTO);

		} else {
			PostEntity byId = postRepository.findById(postId)
					.orElseThrow(EntityNotFoundException::new);
			mappedEntity = postMapper.updatePostEntityFromDTO(postDTO, byId);
		}
		return mappedEntity;
	}
}
