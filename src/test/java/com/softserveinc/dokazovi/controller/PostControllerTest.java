package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;

import static com.softserveinc.dokazovi.controller.EndPoints.POST;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_IMPORTANT;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST_BY_DIRECTION;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PostControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private PostController postController;
	@Mock
	private PostService postService;

	@BeforeEach
	public void init() {
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(postController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.build();
	}

	@Test
	void findLatestPublished_GetWithPagination_isOk() throws Exception {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());
		mockMvc.perform(get(POST + POST_LATEST + "/?page=0"))
				.andExpect(status().isOk());
		verify(postService).findAllByStatus(eq(PostStatus.PUBLISHED), eq(pageable));
	}

	@Test
	void findImportant_GetWithPagination_isOk() throws Exception {
		Pageable pageable = PageRequest.of(0, 3, Sort.by("createdAt").descending());
		mockMvc.perform(get(POST + POST_IMPORTANT + "/?page=0&size=3"))
				.andExpect(status().isOk());
		verify(postService).findImportantPosts(eq(pageable));
	}

	@Test
	void findLatestByDirection() throws Exception {
		Integer directionId = 1;
		Integer typeId = 2;
		Set<Integer> tags = Set.of(3, 4, 5, 6);
		Pageable pageable = PageRequest.of(0, 6, Sort.by("createdAt").descending());
		mockMvc.perform(
				get(POST + POST_LATEST_BY_DIRECTION + "?direction=1&page=0&size=6&type=2&tags=3,4,5,6"))
				.andExpect(status().isOk());
		verify(postService).findAllByMainDirection(directionId, typeId, tags, pageable);
	}
}
