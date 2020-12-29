package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.SourceEntity;
import com.softserveinc.dokazovi.entity.TagEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.error.NotExistsEntityException;
import com.softserveinc.dokazovi.error.UnsupportedCreateOperationException;
import com.softserveinc.dokazovi.mapper.PostMapper;
import com.softserveinc.dokazovi.repositories.PostRepository;
import com.softserveinc.dokazovi.service.DirectionService;
import com.softserveinc.dokazovi.service.SourceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

	@Mock
	private PostRepository postRepository;
	@Mock
	private PostMapper postMapper;
	@Mock
	private Pageable pageable;
	@Mock
	private DirectionService directionService;
	@Mock
	private SourceService sourceService;
	@InjectMocks
	private PostServiceImpl postService;

	private Page<PostEntity> postEntityPage;
	private PostEntity postEntity;
	private Set<DirectionEntity> directionEntities;
	private Set<TagEntity> tagEntities;
	private Set<SourceEntity> sourceEntities;
	private Boolean isExceptionThrown;

	@BeforeEach
	void init() {
		postEntityPage = new PageImpl<>(List.of(new PostEntity(), new PostEntity()));
		directionEntities = new HashSet<>();
		tagEntities = new HashSet<>();
		sourceEntities = new HashSet<>();
		isExceptionThrown = false;
	}

	@Test
	void saveNewPostFromUserWithoutId() {
		PostEntity postEntity = PostEntity.builder().build();
		PostSaveFromUserDTO postDTO = PostSaveFromUserDTO.builder().build();
		when(postRepository.save(any(PostEntity.class))).thenReturn(postEntity);
		when(postMapper.toPostEntity(any(PostSaveFromUserDTO.class))).thenReturn(postEntity);
		postService.saveFromUser(postDTO, null);
		verify(postMapper).toPostDTO(any(PostEntity.class));
		verify(postRepository).save(any(PostEntity.class));
	}

	@Test
	void updatePostFromUserWithId() {
		Optional<PostEntity> postEntity = Optional.ofNullable(PostEntity.builder().id(1).build());
		PostSaveFromUserDTO postDTO = PostSaveFromUserDTO.builder().id(1).build();
		when(postRepository.save(any(PostEntity.class))).thenReturn(postEntity.orElseThrow());
		when(postRepository.findById(any(Integer.class))).thenReturn(postEntity);
		when(postMapper.toPostDTO(any(PostEntity.class))).thenReturn(new PostDTO());
		postService.saveFromUser(postDTO, null);
		verify(postRepository).save(any(PostEntity.class));
		verify(postMapper).toPostDTO(any(PostEntity.class));
		verify(postRepository).findById(any(Integer.class));
		verify(postMapper).updatePostEntityFromDTO(any(PostSaveFromUserDTO.class), any(PostEntity.class));
	}

	@Test
	void validateSave_whenUnsupportedCreateOperationExceptionThrown_thenAssertionSucceeds() {
		directionEntities.add(DirectionEntity.builder().id(null).build());
		postEntity = PostEntity.builder()
				.directions(directionEntities)
				.build();
		try {
			postService.validateSave(postEntity);
		} catch (UnsupportedCreateOperationException e) {
			isExceptionThrown = true;
		}
		assertTrue(isExceptionThrown);
	}

	@Test
	void validateSave_whenNotExistsDirectionEntityExceptionThrown_thenAssertionSucceeds() {
		directionEntities.add(DirectionEntity.builder().id(1).build());
		postEntity = PostEntity.builder()
				.directions(directionEntities)
				.build();
		when(directionService.exists(any(DirectionEntity.class))).thenReturn(false);
		try {
			postService.validateSave(postEntity);
		} catch (NotExistsEntityException e) {
			isExceptionThrown = true;
		}
		assertTrue(isExceptionThrown);
	}

	@Test
	void validateSave_whenNotExistsSourcesEntityExceptionThrown_thenAssertionSucceeds() {
		sourceEntities.add(SourceEntity.builder().id(1).build());
		postEntity = PostEntity.builder()
				.sources(sourceEntities)
				.build();
		when(sourceService.exists(any(SourceEntity.class))).thenReturn(false);
		try {
			postService.validateSave(postEntity);
		} catch (NotExistsEntityException e) {
			isExceptionThrown = true;
		}
		assertTrue(isExceptionThrown);
	}


	@Test
	void findAllByStatus() {
		when(postRepository.findAllByStatus(any(PostStatus.class), any(Pageable.class))).thenReturn(postEntityPage);
		postService.findAllByStatus(PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findImportantPosts() {
		when(postRepository.findAllByImportantIsTrueAndStatus(any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findImportantPosts(pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByDirection() {
		Integer directionId = 1;
		when(postRepository.findAllByDirectionsContainsAndStatus(
				any(DirectionEntity.class), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByDirection(directionId, null, null, PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByDirectionAndType() {
		Integer directionId = 1;
		Set<Integer> types = Set.of(1,2,3);
		when(postRepository.findAllByDirectionsContainsAndTypeIdInAndStatus(
				any(DirectionEntity.class), anySet(), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByDirection(directionId, types, null, PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByDirectionAndTags() {
		Integer directionId = 1;
		Set<Integer> tags = Set.of(1, 2, 3, 4);
		when(postRepository.findAllByDirectionsContainsAndTagsIdInAndStatus(
				any(DirectionEntity.class), anySet(), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByDirection(directionId, null, tags, PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByDirectionAndTypeAndTags() {
		Integer directionId = 1;
		Set<Integer> types = Set.of(1,2,3);
		Set<Integer> tags = Set.of(1, 2, 3, 4);
		when(postRepository.findAllByDirectionsContainsAndTypeIdInAndTagsIdInAndStatus(
				any(DirectionEntity.class), anySet(), anySet(), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByDirection(directionId, types,tags, PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByExpert() {
		Integer expertId = 3;
		when(postRepository.findAllByAuthorIdAndStatus(
				any(Integer.class), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByExpert(expertId, null, PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByExpertAndType() {
		Integer expertId = 5;
		Set<Integer> typeId = Set.of(1, 2);
		when(postRepository.findAllByAuthorIdAndTypeIdInAndStatus(any(Integer.class),
				anySet(), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByExpert(expertId, typeId, PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}
}