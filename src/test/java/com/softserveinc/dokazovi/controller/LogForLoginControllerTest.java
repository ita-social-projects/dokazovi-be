package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.service.LogForLoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.softserveinc.dokazovi.controller.EndPoints.LOG_FOR_LOGIN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LogForLoginControllerTest {
    private MockMvc mockMvc;
    @Mock
    private LogForLoginService logForLoginService;
    @InjectMocks
    private LogForLoginController logForLoginController;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(logForLoginController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    void getLogList() throws Exception {
        mockMvc.perform(get(LOG_FOR_LOGIN)).andExpect(status().isOk());
        verify(logForLoginService).findAllLogs(any(Pageable.class), any(), any(),anyString(), anyString(), anyString());

    }
}
