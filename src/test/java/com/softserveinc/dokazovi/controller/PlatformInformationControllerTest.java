package com.softserveinc.dokazovi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.dokazovi.dto.info.PlatformInformationDTO;
import com.softserveinc.dokazovi.service.PlatformInformationService;
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

import static com.softserveinc.dokazovi.controller.EndPoints.PLATFORM_INFORMATION;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PlatformInformationControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private PlatformInformationController infoController;
	@Mock
	private PlatformInformationService infoService;
	@Mock
	private Validator validator;

	@BeforeEach
	public void init() {
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(infoController)
				.setValidator(validator)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.build();
	}

	@Test
	void getPlatformInfoById_WhenExists_isOk() throws Exception {
		Integer existingInfoId = 1;
		String uri = PLATFORM_INFORMATION + "/" + existingInfoId;

		PlatformInformationDTO infoDTO = PlatformInformationDTO.builder()
				.id(existingInfoId)
				.build();

		when(infoService.getInfoById(any(Integer.class))).thenReturn(infoDTO);
		mockMvc.perform(get(uri)).andExpect(status().isOk());

		verify(infoService).getInfoById(eq(existingInfoId));
	}

	@Test
	void getPlatformInfoById_WhenDoesNotExist_NotFound() throws Exception {
		Integer notExistingInfoId = 1;
		String uri = PLATFORM_INFORMATION + "/" + notExistingInfoId;

		when(infoService.getInfoById(any(Integer.class))).thenReturn(null);
		mockMvc.perform(get(uri)).andExpect(status().isNotFound());

		verify(infoService).getInfoById(eq(notExistingInfoId));
	}

	@Test
	void saveInfo() throws Exception {
		String contentJSON = "{\n"
				+ "  \"id\": 1,\n"
				+ "  \"title\": \"some title\",\n"
				+ "  \"title\": \"some text\"\n"
				+ "}";

		ObjectMapper mapper = new ObjectMapper();
		PlatformInformationDTO infoDTO = mapper.readValue(contentJSON, PlatformInformationDTO.class);
		mockMvc.perform(post(PLATFORM_INFORMATION)
				.contentType(MediaType.APPLICATION_JSON)
				.content(contentJSON))
				.andExpect(status().isCreated());
		verify(infoService).saveInfo(any(), eq(infoDTO));
	}
}
