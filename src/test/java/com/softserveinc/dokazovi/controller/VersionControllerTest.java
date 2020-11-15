package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.util.BuildVersion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class VersionControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private BuildVersion buildVersion;

	@Test
	void shouldReturnBuildVersion() throws Exception {
		String version = String.format("{\"version\":\"%s\"}", buildVersion.getVersion());
		this.mockMvc.perform(MockMvcRequestBuilders.get("/version"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().string(version));
	}
}