package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.service.RegionService;
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
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.softserveinc.dokazovi.controller.EndPoints.REGION;
import static com.softserveinc.dokazovi.controller.EndPoints.REGION_ALL;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RegionControllerTest {

	private MockMvc mockMvc;

	@Mock
	private RegionService regionService;
	@InjectMocks
	private RegionController regionController;

	@BeforeEach
	void setUp() {
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(regionController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.build();
	}

	@Test
	void getAllRegions() throws Exception {
		Pageable pageable = PageRequest.of(0, 2000);
		String uri = REGION + REGION_ALL;

		mockMvc.perform(get(uri)).andExpect(status().isOk());

		verify(regionService).findAllRegions(pageable);
	}
}