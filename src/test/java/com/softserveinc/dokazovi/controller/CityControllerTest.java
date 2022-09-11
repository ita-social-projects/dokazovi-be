package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.service.CityService;
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

import static com.softserveinc.dokazovi.controller.EndPoints.CITY;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CityControllerTest {

    private MockMvc mockMvc;
    @Mock
    private CityService cityService;
    @InjectMocks
    private CityController cityController;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(cityController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    void getAllCities() throws Exception {
        mockMvc.perform(get(CITY)).andExpect(status().isOk());

        verify(cityService).findAllCities();
    }

    @Test
    void getAllCitiesByRegion() throws Exception {
        mockMvc.perform(get(CITY + "/" + any(Integer.class))).andExpect(status().isOk());

        verify(cityService).findAllCitiesByRegion(any(Integer.class));
    }
}