package com.softserveinc.dokazovi.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.info.BuildProperties;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Properties;

import static com.softserveinc.dokazovi.controller.EndPoints.VERSION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VersionControllerTest {

    private final BuildProperties buildProperties = new BuildProperties(new Properties());
    private final VersionController versionController = new VersionController(buildProperties);

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(versionController)
                .build();
    }

    @Test
    void getBuildVersion() throws Exception {
        mockMvc
                .perform(get(VERSION))
                .andExpect(status().isOk());
    }
}
