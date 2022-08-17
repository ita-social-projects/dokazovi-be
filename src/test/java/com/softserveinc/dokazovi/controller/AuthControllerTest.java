package com.softserveinc.dokazovi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.dokazovi.dto.payload.LoginRequest;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.exception.BadRequestException;
import com.softserveinc.dokazovi.security.TokenProvider;
import com.softserveinc.dokazovi.service.ProviderService;
import com.softserveinc.dokazovi.service.UserService;
import com.softserveinc.dokazovi.service.impl.MailSenderServiceImpl;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.softserveinc.dokazovi.controller.EndPoints.AUTH;
import static com.softserveinc.dokazovi.controller.EndPoints.AUTH_LOGIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AuthControllerTest {

    private MockMvc mockMvc;
    @Mock
    private TokenProvider tokenProvider;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private MailSenderServiceImpl mailSenderServiceImpl;
    @Mock
    private ProviderService providerService;
    @Mock
    private UserService userService;
    @InjectMocks
    private AuthController authController;

    private static final String EMAIL = "user@mail.com";
    private static final String PASSWORD = "user";
    private static final String TOKEN = "950c9760-805e-449c-a966-2d0d5ebd86f4";
    private static final String URI = AUTH + AUTH_LOGIN;
    public static final String BAD_REQUEST_MESSAGE = "Please confirm your email!";

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(authController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(EMAIL);
        loginRequest.setPassword(PASSWORD);
    }

    @Test
    void loginUser() throws Exception {
        LoginRequest loginRequest = createLoginRequest(EMAIL, PASSWORD);
        Authentication authentication = authenticate(loginRequest);
        UserEntity user = buildUserEntity(EMAIL, PASSWORD, true);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(tokenProvider.createToken(authentication)).thenReturn(TOKEN);
        when(userService.findByEmail(anyString())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                        .content(asJsonString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().equals(TOKEN));
        verify(authenticationManager, times(1))
                .authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userService, times(1))
                .findByEmail(anyString());
        verify(tokenProvider, times(1))
                .createToken(authentication);
    }

    @Test
    void loginUser_WhenEnabledFalse_ThrowException() throws Exception {
        LoginRequest loginRequest = createLoginRequest(EMAIL, PASSWORD);
        Authentication authentication = authenticate(loginRequest);
        UserEntity user = buildUserEntity(EMAIL, PASSWORD, false);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(tokenProvider.createToken(any(Authentication.class))).thenReturn(TOKEN);
        when(userService.findByEmail(anyString())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                        .content(asJsonString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertTrue(result.getResolvedException()
                                instanceof
                                BadRequestException))
                .andExpect(result ->
                        assertEquals(
                                BAD_REQUEST_MESSAGE,
                                result.getResolvedException().getMessage()));
        verify(userService, times(1))
                .findByEmail(anyString());
    }

    private LoginRequest createLoginRequest(String email, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        return loginRequest;
    }

    private Authentication authenticate(LoginRequest loginRequest) {
        return new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
        );
    }

    private  UserEntity buildUserEntity(String email, String password, boolean enabled) {
        return UserEntity.builder()
                .email(email)
                .password(password)
                .enabled(enabled)
                .build();
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
