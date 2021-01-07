package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.exception.InvalidIdEntityException;
import com.softserveinc.dokazovi.mapper.PostMapper;
import com.softserveinc.dokazovi.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

	@InjectMocks
	private PostServiceImpl postService;

	private Page<PostEntity> postEntityPage;

	@BeforeEach
	void init() {
		postEntityPage = new PageImpl<>(List.of(new PostEntity(), new PostEntity()));
	}

	@Test
	void saveFromUser_WhenIdIsNull() {
		when(postMapper.toPostEntity(any(PostSaveFromUserDTO.class))).thenReturn(new PostEntity());
		postService.saveFromUser(new PostSaveFromUserDTO(), new UserEntity());
		verify(postMapper, times(1)).toPostEntity(any(PostSaveFromUserDTO.class));
		verify(postRepository, times(1)).save(any(PostEntity.class));
		verify(postMapper, times(1)).toPostDTO(any());
	}

	@Test
	void saveFromUser_WhenIdIsPresent_isOk() {
		when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(new PostEntity()));
		when(postMapper.updatePostEntityFromDTO(any(), any())).thenReturn(new PostEntity());
		PostSaveFromUserDTO dto = PostSaveFromUserDTO.builder()
				.id(1)
				.build();
		postService.saveFromUser(dto, new UserEntity());
		verify(postMapper, times(1)).updatePostEntityFromDTO(any(), any());
		verify(postRepository, times(1)).findById(any());
		verify(postMapper, times(1)).toPostDTO(any());
	}

	@Test
	void saveFromUser_WhenIdIsWrong_ThrowException() {
		when(postRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
		PostSaveFromUserDTO dto = PostSaveFromUserDTO.builder()
				.id(1)
				.build();
		UserEntity userEntity = new UserEntity();
		assertThrows(InvalidIdEntityException.class, () -> postService.saveFromUser(dto, userEntity));
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
		Set<Integer> types = Set.of(1, 2, 3);
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
		Set<Integer> types = Set.of(1, 2, 3);
		Set<Integer> tags = Set.of(1, 2, 3, 4);
		when(postRepository.findAllByDirectionsContainsAndTypeIdInAndTagsIdInAndStatus(
				any(DirectionEntity.class), anySet(), anySet(), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByDirection(directionId, types, tags, PostStatus.PUBLISHED, pageable);
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