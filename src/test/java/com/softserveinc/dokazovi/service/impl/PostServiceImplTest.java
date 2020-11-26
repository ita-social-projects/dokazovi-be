package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.mapper.PostMapper;
import com.softserveinc.dokazovi.repositories.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;


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

	@Test
	void findAllByStatus() {
		Page<PostEntity> postEntityPage = new PageImpl<>(List.of(new PostEntity(), new PostEntity()));
		when(postRepository.findAllByStatus(any(PostStatus.class), any(Pageable.class))).thenReturn(postEntityPage);
		postService.findAllByStatus(PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toLatestPostDTO(any(PostEntity.class));
	}

	@Test
	void findImportantPosts() {
		Page<PostEntity> postEntityPage = new PageImpl<>(List.of(new PostEntity(), new PostEntity()));
		when(postRepository.findAllByImportantIsTrueAndStatus(any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findImportantPosts(pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toImportantPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByMainDirection() {
		Page<PostEntity> postEntityPage = new PageImpl<>(List.of(new PostEntity(), new PostEntity()));
		Integer directionId = 1;
		when(postRepository.findAllByMainDirectionId(any(Integer.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByMainDirection(directionId, null, null, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toLatestPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByMainDirectionAndType() {
		Page<PostEntity> postEntityPage = new PageImpl<>(List.of(new PostEntity(), new PostEntity()));
		Integer directionId = 1;
		Integer typeId = 1;
		when(postRepository.findAllByMainDirectionIdAndTypeId(any(Integer.class),
				any(Integer.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByMainDirection(directionId, typeId, null, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toLatestPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByMainDirectionAndTags() {
		Page<PostEntity> postEntityPage = new PageImpl<>(List.of(new PostEntity(), new PostEntity()));
		Integer directionId = 1;
		Set<Integer> sets = Set.of(1, 2, 3, 4);
		when(postRepository.findAllByMainDirectionIdAndTagsIdIn(any(Integer.class), anySet(), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByMainDirection(directionId, null, sets, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toLatestPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByMainDirectionAndTypeAndTags() {
		Page<PostEntity> postEntityPage = new PageImpl<>(List.of(new PostEntity(), new PostEntity()));
		Integer directionId = 1;
		Integer typeId = 1;
		Set<Integer> sets = Set.of(1, 2, 3, 4);
		when(postRepository.findAllByMainDirectionIdAndTypeIdAndTagsIdIn(any(Integer.class),
				any(Integer.class), anySet(), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByMainDirection(directionId, typeId, sets, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toLatestPostDTO(any(PostEntity.class));
	}
}