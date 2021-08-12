package com.softserveinc.dokazovi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.dokazovi.dto.foreignexpert.ForeignExpertSaveDTO;
import com.softserveinc.dokazovi.service.ForeignExpertService;
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

import static com.softserveinc.dokazovi.controller.EndPoints.FOREIGN_EXPERT;
import static com.softserveinc.dokazovi.controller.EndPoints.FOREIGN_EXPERT_SEARCH;
import static com.softserveinc.dokazovi.controller.EndPoints.POST;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ForeignExpertControllerTest {
	private MockMvc mockMvc;

	@InjectMocks
	private ForeignExpertController foreignExpertController;

	@Mock
	private ForeignExpertService foreignExpertService;

	@BeforeEach
	public void init() {
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(foreignExpertController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.build();
	}

	@Test
	void saveForeignExpert() throws Exception {
		String content = "{\n"
				+ "  \"avatar\": \"some avatar\",\n"
				+ "  \"bio\": \"some bio\",\n"
				+ "  \"fullName\": \"some full name\",\n"
				+ "  \"id\": 1\n"
				+ "}";
		ObjectMapper mapper = new ObjectMapper();
		ForeignExpertSaveDTO fe = mapper.readValue(content, ForeignExpertSaveDTO.class);
		mockMvc.perform(post(FOREIGN_EXPERT)
						.contentType(MediaType.APPLICATION_JSON)
						.content(content))
				.andExpect(status().isCreated());
		verify(foreignExpertService).save(eq(fe), any());
	}

	@Test
	void search() throws Exception {
		mockMvc.perform(get(FOREIGN_EXPERT + FOREIGN_EXPERT_SEARCH + "?query=foo")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		verify(foreignExpertService).search(eq("foo"), any());
	}

}
