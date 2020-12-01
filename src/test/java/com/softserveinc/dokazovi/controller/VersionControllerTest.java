package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.util.BuildVersion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.softserveinc.dokazovi.controller.EndPoints.VERSION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VersionControllerTest {

	private MockMvc mockMvc;

	@BeforeEach
	void init() {
		BuildVersion version = new BuildVersion();
		version.setVersion("BUILD_VERSION:0.0.0");
		VersionController versionController = new VersionController(version);
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(versionController)
				.build();
	}
	@Test
	void getBuildVersion_Get_isOk() throws Exception {
		mockMvc
				.perform(get(VERSION))
				.andExpect(status().isOk());
	}
}