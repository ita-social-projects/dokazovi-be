package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.entity.VerificationToken;
import com.softserveinc.dokazovi.service.UserService;
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

import static com.softserveinc.dokazovi.controller.EndPoints.AUTH;
import static com.softserveinc.dokazovi.controller.EndPoints.AUTH_VERIFICATION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AuthControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;
    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(authController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    void registrationComplete() throws Exception {
        String token = "950c9760-805e-449c-a966-2d0d5ebd86f4";
        String uri = AUTH + AUTH_VERIFICATION + "?token=" + token;
        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .build();
        when(userService.getVerificationToken(anyString())).thenReturn(verificationToken);
        mockMvc.perform(get(uri)).andExpect(status().isOk());
        verify(userService).getVerificationToken(token);
        assertEquals(token, verificationToken.getToken());
    }
}
