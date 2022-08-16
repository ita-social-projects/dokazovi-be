package com.softserveinc.dokazovi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import static com.softserveinc.dokazovi.controller.EndPoints.POST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AuthorControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private AuthorController authorController;

	@Mock
	private AuthorService authorService;

	@Mock
	private Validator validator;

	@BeforeEach
	public void init() {
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(authorController)
				.setValidator(validator)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.build();
	}

	@Test
	void createAuthor() throws Exception {
		String content = "{\n"
				+ "  \"authorId\": 1,\n"
				+ "  \"content\": \"string\",\n"
				+ "  \"directions\": [\n"
				+ "    {\n"
				+ "      \"id\": 0\n"
				+ "    }\n"
				+ "  ],\n"
				+ "  \"origins\": [\n"
				+ "    {\n"
				+ "      \"id\": 0\n"
				+ "    }\n"
				+ "  ],\n"
				+ "  \"preview\": \"string\",\n"
				+ "  \"previewImageUrl\": \"string\",\n"
				+ "  \"title\": \"string\",\n"
				+ "  \"type\": {\n"
				+ "    \"id\": 0\n"
				+ "  },\n"
				+ "  \"videoUrl\": \"string\"\n"
				+ "}";
		ObjectMapper mapper = new ObjectMapper();
		PostSaveFromUserDTO post = mapper.readValue(content, PostSaveFromUserDTO.class);
		mockMvc.perform(post(POST)
						.contentType(MediaType.APPLICATION_JSON)
						.content(content))
				.andExpect(status().is(404));
	}

	@Test
	void updateAuthor() throws Exception {
		String content = "{\n"
				+ "  \"authorId\": 1,\n"
				+ "  \"content\": \"string\",\n"
				+ "  \"directions\": [\n"
				+ "    {\n"
				+ "      \"id\": 0\n"
				+ "    }\n"
				+ "  ],\n"
				+ "  \"origins\": [\n"
				+ "    {\n"
				+ "      \"id\": 0\n"
				+ "    }\n"
				+ "  ],\n"
				+ "  \"preview\": \"string\",\n"
				+ "  \"previewImageUrl\": \"string\",\n"
				+ "  \"title\": \"string\",\n"
				+ "  \"type\": {\n"
				+ "    \"id\": 0\n"
				+ "  },\n"
				+ "  \"videoUrl\": \"string\"\n"
				+ "}";
		ObjectMapper mapper = new ObjectMapper();
		PostSaveFromUserDTO post = mapper.readValue(content, PostSaveFromUserDTO.class);
		mockMvc.perform(post(POST)
						.contentType(MediaType.APPLICATION_JSON)
						.content(content))
				.andExpect(status().is(404));
	}
}
