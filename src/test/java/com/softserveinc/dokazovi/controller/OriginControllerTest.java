package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.service.OriginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.softserveinc.dokazovi.controller.EndPoints.ORIGIN;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OriginControllerTest {

	private MockMvc mockMvc;

	@Mock
	private OriginService originService;
	@InjectMocks
	private OriginController originController;

	@BeforeEach
	void setUp() {
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(originController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.build();
	}

	@Test
	void getAllOrigins() throws Exception {
		mockMvc.perform(get(ORIGIN)).andExpect(status().isOk());

		verify(originService).findAllOrigins();
	}

	@Test
	void getAllOriginsByUserId() throws Exception {
		String uri = ORIGIN + "/1";

		mockMvc.perform(get(uri)).andExpect(status().isOk());

		verify(originService).findAllOriginsByUserId(1);
	}
}