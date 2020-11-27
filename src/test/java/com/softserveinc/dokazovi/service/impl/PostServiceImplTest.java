package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.post.PostLatestByDirectionFilterDTO;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
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

	private Page<PostEntity> postEntityPage;

	@BeforeEach
	void init() {
		postEntityPage = new PageImpl<>(List.of(new PostEntity(), new PostEntity()));
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
	void findAllByMainDirection() {
		PostLatestByDirectionFilterDTO postParamsDTO = PostLatestByDirectionFilterDTO
				.builder()
				.direction(1)
				.build();
		when(postRepository.findAllByMainDirectionIdAndStatus(
				any(Integer.class), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByMainDirection(postParamsDTO, PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByMainDirectionAndType() {
		PostLatestByDirectionFilterDTO postParamsDTO = PostLatestByDirectionFilterDTO
				.builder()
				.direction(1)
				.type(2)
				.build();
		when(postRepository.findAllByMainDirectionIdAndTypeIdAndStatus(any(Integer.class),
				any(Integer.class), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByMainDirection(postParamsDTO, PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByMainDirectionAndTags() {
		PostLatestByDirectionFilterDTO postParamsDTO = PostLatestByDirectionFilterDTO
				.builder()
				.direction(1)
				.tags(Set.of(3, 4, 5, 6))
				.build();
		when(postRepository.findAllByMainDirectionIdAndTagsIdInAndStatus(
				any(Integer.class), anySet(), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByMainDirection(postParamsDTO, PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByMainDirectionAndTypeAndTags() {
		PostLatestByDirectionFilterDTO postParamsDTO = PostLatestByDirectionFilterDTO
				.builder()
				.direction(1)
				.type(2)
				.tags(Set.of(3, 4, 5, 6))
				.build();
		when(postRepository.findAllByMainDirectionIdAndTypeIdAndTagsIdInAndStatus(any(Integer.class),
				any(Integer.class), anySet(), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByMainDirection(postParamsDTO, PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}
}