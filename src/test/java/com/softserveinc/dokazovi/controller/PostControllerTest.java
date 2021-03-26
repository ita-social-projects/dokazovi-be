package com.softserveinc.dokazovi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.exception.EntityNotFoundException;
import com.softserveinc.dokazovi.service.PostService;
import com.softserveinc.dokazovi.service.PostTypeService;
import org.junit.jupiter.api.Assertions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Set;

import static com.softserveinc.dokazovi.controller.EndPoints.POST;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_ALL_POSTS;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_IMPORTANT;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST_BY_DIRECTION;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST_BY_EXPERT;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_TYPE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

	@Mock
	private Validator validator;

	@BeforeEach
	public void init() {
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(postController)
				.setValidator(validator)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.build();
	}

	@Test
	void getPostById_WhenExists_isOk() throws Exception {
		Integer existingPostId = 1;
		String uri = POST + "/" + existingPostId;
		PostDTO postDTO = PostDTO.builder()
				.id(existingPostId)
				.build();

		when(postService.findPostById(any(Integer.class))).thenReturn(postDTO);
		mockMvc.perform(get(uri)).andExpect(status().isOk());

		verify(postService).findPostById(eq(existingPostId));
	}

	@Test
	void getPostById_WhenNotExists_NotFound() throws Exception {
		Integer notExistingPostId = 1;
		String uri = POST + "/" + notExistingPostId;

		when(postService.findPostById(any(Integer.class))).thenReturn(null);
		mockMvc.perform(get(uri)).andExpect(status().isNotFound());

		verify(postService).findPostById(eq(notExistingPostId));
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
				"  \"videoUrl\": \"string\",\n" +
				"  \"previewImageUrl\": \"string\",\n" +
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

	@Test
	void archivePostById_WhenExists_isOk() throws Exception {
		Integer existingPostId = 1;
		Mockito.when(postService.archivePostById(existingPostId)).thenReturn(true);

		mockMvc.perform(delete("/post/1")).andExpect(status().isOk()).andExpect(result ->
				Assertions.assertEquals("{\"success\":true,\"message\":\"post 1 deleted successfully\"}",
						result.getResponse().getContentAsString()));
	}

	@Test
	void archivePostById_WhenNotExists_NotFound() throws Exception {
		Integer notExistingPostId = -1;
		Mockito.when(postService.archivePostById(notExistingPostId))
				.thenThrow(new EntityNotFoundException(String.format("Post with %s not found", notExistingPostId)));

		mockMvc.perform(delete("/post/-1")).andExpect(status().isOk()).andExpect(result ->
				Assertions.assertEquals("{\"success\":false,\"message\":\"Post with -1 not found\"}",
						result.getResponse().getContentAsString()));
	}

	@Test
	void archivePostById_WhenNotExists_NotFound_ThrowException() {
		Integer notExistingPostId = -1;

		Mockito.when(postService.archivePostById(notExistingPostId))
				.thenThrow(new EntityNotFoundException(String.format("Post with %s not found", notExistingPostId)));
		Assertions.assertThrows(EntityNotFoundException.class, () -> postService.archivePostById(notExistingPostId));
	}

	@Test
	void findAllPostsByDirectionsByPostTypesAndByOrigins_isOk() throws Exception {
		Set<Integer> directions = Set.of(1, 2);
		Set<Integer> types = Set.of(1, 3);
		Set<Integer> origins = Set.of(2, 3);
		Pageable pageable = PageRequest.of(0, 10);
		PostDTO postDTO = PostDTO.builder()
				.id(1)
				.build();
		Page<PostDTO> page = new PageImpl<>(List.of(postDTO));
		Mockito.when(postService.findAllByDirectionsAndByPostTypesAndByOrigins(directions, types, origins, pageable))
				.thenReturn(page);
		mockMvc.perform(get(POST + POST_ALL_POSTS + "?directions=1,2&types=1,3&origins=2,3"))
				.andExpect(status().isOk())
				.andExpect(result -> Assertions.assertEquals(1,
						getIdFromResponse(result.getResponse().getContentAsString()))
				);

		verify(postService).findAllByDirectionsAndByPostTypesAndByOrigins(directions, types, origins, pageable);
	}

	private Integer getIdFromResponse(String json) {
		try {
			JSONArray jsonArray = new JSONObject(json).getJSONArray("content");
			JSONObject obj = (JSONObject) jsonArray.get(0);
			return obj.getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Test
	void findAllPostsByDirectionsByPostTypesAndByOrigins_CatchException() throws Exception {
		Set<Integer> directionIds = null;
		Set<Integer> typeIds = null;
		Set<Integer> originIds = null;
		Pageable pageable = PageRequest.of(0, 10);
		Page<PostDTO> page = null;

		Mockito.when(
				postService.findAllByDirectionsAndByPostTypesAndByOrigins(directionIds, typeIds, originIds, pageable))
				.thenThrow(new EntityNotFoundException(
						String.format("Fail to filter posts with params directionIds=%s, typeIds=%s, originIds=%s",
								directionIds, typeIds, originIds)))
				.thenReturn(page);

		mockMvc.perform(get(POST + POST_ALL_POSTS))
				.andExpect(status().isNoContent())
				.andExpect(result -> Assertions.assertEquals(0, result.getResponse().getContentLength()));
		verify(postService).findAllByDirectionsAndByPostTypesAndByOrigins(directionIds, typeIds, originIds, pageable);
	}

	@Test
	void findAllPostsByDirectionsByPostTypesAndByOrigins_EmptyPage() throws Exception {
		Set<Integer> directions = Set.of(-1, 1111);
		Set<Integer> types = Set.of(123, 2345);
		Set<Integer> origins = Set.of(1234, 1231);
		Pageable pageable = PageRequest.of(0, 10);
		PostDTO postDTO = PostDTO.builder()
				.id(0)
				.build();
		Page<PostDTO> page = new PageImpl<>(List.of(postDTO));

		Mockito.when(postService.findAllByDirectionsAndByPostTypesAndByOrigins(directions, types, origins, pageable))
				.thenReturn(page);
		mockMvc.perform(get(POST + POST_ALL_POSTS + "?directions=-1,1111&types=123,2345&origins=1234,1231"))
				.andExpect(status().isOk())
				.andExpect(result -> Assertions.assertEquals(0,
						getIdFromResponse(result.getResponse().getContentAsString()))
				);

		verify(postService).findAllByDirectionsAndByPostTypesAndByOrigins(directions, types, origins, pageable);
	}
}
