package com.softserveinc.dokazovi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.dokazovi.dto.tag.TagSaveDTO;
import com.softserveinc.dokazovi.service.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.softserveinc.dokazovi.controller.EndPoints.TAG;
import static com.softserveinc.dokazovi.controller.EndPoints.TAG_FIND_BY_VALUE;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TagControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private TagController tagController;
	@Mock
	private TagService tagService;

	@BeforeEach
	public void init() {
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(tagController)
				.build();
	}

	@Test
	void findByValue() throws Exception {
		String value = "a";
		int limit = 5;
		mockMvc.perform(get(TAG + TAG_FIND_BY_VALUE + "/?value=" + value + "&limit=" + limit))
				.andExpect(status().isOk());
		verify(tagService).findTagsByValue(value, limit);
	}

	@Test
	void saveTag() throws Exception {
		String content = "{\n" +
				"  \"tag\": \"someTag\"" +
				"}";
		ObjectMapper mapper = new ObjectMapper();
		TagSaveDTO tagSaveDTO = mapper.readValue(content, TagSaveDTO.class);
		mockMvc.perform(post(TAG)
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
				.andExpect(status().isCreated());
		verify(tagService).save(eq(tagSaveDTO));
	}
}