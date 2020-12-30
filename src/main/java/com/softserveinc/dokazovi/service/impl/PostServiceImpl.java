package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.PostTypeEntity;
import com.softserveinc.dokazovi.entity.SourceEntity;
import com.softserveinc.dokazovi.entity.TagEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.error.InvalidIdEntityException;
import com.softserveinc.dokazovi.error.NotExistsEntityException;
import com.softserveinc.dokazovi.error.NotUniqueEntityException;
import com.softserveinc.dokazovi.error.UnsupportedCreateOperationException;
import com.softserveinc.dokazovi.mapper.PostMapper;
import com.softserveinc.dokazovi.repositories.PostRepository;
import com.softserveinc.dokazovi.service.DirectionService;
import com.softserveinc.dokazovi.service.PostService;
import com.softserveinc.dokazovi.service.PostTypeService;
import com.softserveinc.dokazovi.service.SourceService;
import com.softserveinc.dokazovi.service.TagService;
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
	private final PostTypeService postTypeService;
	private final DirectionService directionService;
	private final TagService tagService;
	private final SourceService sourceService;

	@Override
	public PostDTO saveFromUser(PostSaveFromUserDTO postSaveDTO, UserEntity user) {
		Integer postId = postSaveDTO.getId();

		if (postId == null) {
			PostEntity postEntity = postMapper.toPostEntity(postSaveDTO);
			validateSave(postEntity);
			postEntity.setAuthor(user);
			postEntity.setStatus(PostStatus.MODERATION_FIRST_SIGN);
			PostEntity savedEntity = postRepository.save(postEntity);
			return postMapper.toPostDTO(savedEntity);
		}

		return postRepository.findById(postId)
				.map(postEntity -> {
					postMapper.updatePostEntityFromDTO(postSaveDTO, postEntity);
					validateSave(postEntity);
					postEntity.setStatus(PostStatus.MODERATION_FIRST_SIGN);
					PostEntity save = postRepository.save(postEntity);
					return postMapper.toPostDTO(save);
				})
				.orElseThrow(() -> new InvalidIdEntityException(postSaveDTO));
	}

	@Override
	public void validateSave(PostEntity postEntity) {
		PostTypeEntity type = postEntity.getType();
		if (type != null) {
			if (type.getId() == null) {
				throw new UnsupportedCreateOperationException(type);
			}
			if (!postTypeService.exists(type)) {
				throw new NotExistsEntityException(type);
			}
		}

		Set<DirectionEntity> directions = postEntity.getDirections();
		if (directions != null) {
			directions.forEach(directionEntity -> {
				if (directionEntity.getId() == null) {
					throw new UnsupportedCreateOperationException(directionEntity);
				}
				if (!directionService.exists(directionEntity)) {
					throw new NotExistsEntityException(directionEntity);
				}
			});
		}

		Set<TagEntity> tags = postEntity.getTags();
		if (tags != null) {
			tags.forEach(tagEntity -> {
				if (tagEntity.getId() == null && !tagService.isUnique(tagEntity)) {
					throw new NotUniqueEntityException(tagEntity);
				}
				if (tagEntity.getId() != null && !tagService.exists(tagEntity)) {
					throw new NotExistsEntityException(tagEntity);
				}
			});
		}

		Set<SourceEntity> sources = postEntity.getSources();
		if (sources != null) {
			sources.forEach(sourceEntity -> {
				if (sourceEntity.getId() != null && !sourceService.exists(sourceEntity)) {
					throw new NotExistsEntityException(sourceEntity);
				}
			});
		}
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
