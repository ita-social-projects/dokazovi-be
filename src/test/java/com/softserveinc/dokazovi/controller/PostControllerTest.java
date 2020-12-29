package com.softserveinc.dokazovi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.service.PostService;
import com.softserveinc.dokazovi.service.PostTypeService;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;

import static com.softserveinc.dokazovi.controller.EndPoints.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PostControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private PostController postController;
	@Mock
	private PostService postService;
	@Mock
	private PostTypeService postTypeService;

	@BeforeEach
	public void init() {
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(postController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.build();
	}

	@Test
	void savePost() throws Exception {
		String content = "{\n" +
				"  \"content\": \"string\",\n" +
				"  \"directions\": [\n" +
				"    {\n" +
				"      \"id\": 1\n" +
				"    }\n" +
				"  ],\n" +
				"  \"id\": 1,\n" +
				"  \"preview\": \"string\",\n" +
				"  \"sources\": [\n" +
				"    {\n" +
				"      \"id\": 1\n" +
				"    }\n" +
				"  ],\n" +
				"  \"status\": \"ARCHIVED\",\n" +
				"  \"tags\": [\n" +
				"    {\n" +
				"      \"id\": 1,\n" +
				"      \"tag\": \"string\"\n" +
				"    }\n" +
				"  ],\n" +
				"  \"title\": \"string\",\n" +
				"  \"type\": {\n" +
				"    \"id\": 1,\n" +
				"    \"name\": \"string\"\n" +
				"  }\n" +
				"}";
		ObjectMapper mapper = new ObjectMapper();
		PostSaveFromUserDTO post = mapper.readValue(content, PostSaveFromUserDTO.class);
		mockMvc.perform(post(POST)
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
				.andExpect(status().isCreated());
		verify(postService).saveFromUser(eq(post), any());
	}

	@Test
	void findLatestPublished_GetWithPagination_isOk() throws Exception {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt", "id").descending());
		mockMvc.perform(get(POST + POST_LATEST + "/?page=0"))
				.andExpect(status().isOk());
		verify(postService).findAllByStatus(eq(PostStatus.PUBLISHED), eq(pageable));
	}

	@Test
	void findImportant_GetWithPagination_isOk() throws Exception {
		Pageable pageable = PageRequest.of(0, 3, Sort.by("createdAt", "id").descending());
		mockMvc.perform(get(POST + POST_IMPORTANT + "/?page=0&size=3"))
				.andExpect(status().isOk());
		verify(postService).findImportantPosts(eq(pageable));
	}

	@Test
	void findLatestByDirection() throws Exception {
		Integer directionId = 1;
		Set<Integer> type = Set.of(2);
		Set<Integer> tag = Set.of(3, 4, 5, 6);
		Pageable pageable = PageRequest.of(0, 6, Sort.by("createdAt", "id").descending());
		mockMvc.perform(
				get(POST + POST_LATEST_BY_DIRECTION + "?direction=1&page=0&size=6&type=2&tag=3,4,5,6"))
				.andExpect(status().isOk());
		verify(postService).findAllByDirection(directionId, type, tag, PostStatus.PUBLISHED, pageable);
	}

	@Test
	void findLatestByExpert() throws Exception {
		Integer expertId = 2;
		Set<Integer> typeId = Set.of(1, 2);
		Pageable pageable = PageRequest.of(0, 9, Sort.by("createdAt", "id").descending());
		mockMvc.perform(
				get(POST + POST_LATEST_BY_EXPERT + "?expert=2&page=0&size=9&type=1,2"))
				.andExpect(status().isOk());
		verify(postService).findAllByExpert(expertId, typeId, PostStatus.PUBLISHED, pageable);
	}

	@Test
	void findAllPostType() throws Exception {
		mockMvc.perform(get(POST + POST_TYPE)).andExpect(status().isOk());
		verify(postTypeService).findAll();
	}
}
