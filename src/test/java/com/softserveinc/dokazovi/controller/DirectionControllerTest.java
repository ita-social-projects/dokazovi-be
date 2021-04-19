package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.service.DirectionService;
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

import static com.softserveinc.dokazovi.controller.EndPoints.DIRECTION;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DirectionControllerTest {

	private MockMvc mockMvc;

	@Mock
	private DirectionService directionService;
	@InjectMocks
	private DirectionController directionController;

	@BeforeEach
	void setUp() {
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(directionController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.build();
	}

	@Test
	void getAllDirections() throws Exception {
		mockMvc.perform(get(DIRECTION)).andExpect(status().isOk());

		verify(directionService).findAllDirections();
	}

	@Test
	void getAllDirectionsByUserId() throws Exception {
		String uri = DIRECTION + "/1";

		mockMvc.perform(get(uri)).andExpect(status().isOk());

		verify(directionService).findAllDirectionsByUserId(1);

	}

}