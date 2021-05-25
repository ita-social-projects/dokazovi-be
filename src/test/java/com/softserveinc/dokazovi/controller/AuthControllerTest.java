package com.softserveinc.dokazovi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.dokazovi.dto.payload.LoginRequest;
import com.softserveinc.dokazovi.dto.payload.SignUpRequest;
import com.softserveinc.dokazovi.entity.ProviderEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.VerificationToken;
import com.softserveinc.dokazovi.security.TokenProvider;
import com.softserveinc.dokazovi.service.ProviderService;
import com.softserveinc.dokazovi.service.UserService;
import com.softserveinc.dokazovi.util.MailSenderUtil;
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

import java.util.Optional;

import static com.softserveinc.dokazovi.controller.EndPoints.AUTH;
import static com.softserveinc.dokazovi.controller.EndPoints.AUTH_LOGIN;
import static com.softserveinc.dokazovi.controller.EndPoints.AUTH_SIGNUP;
import static com.softserveinc.dokazovi.controller.EndPoints.AUTH_VERIFICATION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private MailSenderUtil mailSenderUtil;
    @Mock
    private ProviderService providerService;
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
    void loginUser() throws Exception {
        String email = "user@mail.com";
        String password = "user";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        String token = "950c9760-805e-449c-a966-2d0d5ebd86f4";
        String uri = AUTH + AUTH_LOGIN;
        UserEntity user = UserEntity.builder()
                .email(email)
                .password(password)
                .enabled(true)
                .build();
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(tokenProvider.createToken(any(Authentication.class))).thenReturn(token);
        when(userService.findByEmail(anyString())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(asJsonString(loginRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(authenticationManager, times(2))
                .authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userService, times(1))
                .findByEmail(anyString());
    }

    @Test
    void registerUser() throws Exception {
        String email = "user@mail.com";
        String password = "user";
        UserEntity user = UserEntity.builder()
                .email(email)
                .firstName("user")
                .password(password)
                .build();
        SignUpRequest request = new SignUpRequest();
        request.setEmail(user.getEmail());
        request.setName(user.getFirstName());
        request.setPassword(user.getPassword());
        Optional<ProviderEntity> providerEntity = Optional.ofNullable(ProviderEntity.builder().build());
        when(providerService.existsByLocalEmail(anyString())).thenReturn(false);
        when(userService.registerNewUser(any(SignUpRequest.class))).thenReturn(user);
        when(providerService.createLocalProviderEntityForUser(any(UserEntity.class), anyString()))
                .thenReturn(providerEntity);
        doNothing().when(mailSenderUtil).sendMessage(any(UserEntity.class));
        String uri = AUTH + AUTH_SIGNUP;
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        assertEquals(email, user.getEmail());
        verify(providerService, times(1))
                .existsByLocalEmail(anyString());
        verify(userService, times(1))
                .registerNewUser(any(SignUpRequest.class));
        verify(mailSenderUtil, times(1))
                .sendMessage(any(UserEntity.class));
        verify(providerService, times(1))
                .createLocalProviderEntityForUser(any(UserEntity.class), anyString());
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
        verify(userService, times(1))
                .getVerificationToken(anyString());
        assertEquals(token, verificationToken.getToken());
    }

    @Test
    void registrationIncompleteIfTokenIsNull() throws Exception {
        String uri = AUTH + AUTH_VERIFICATION + "?token=" + null;
        VerificationToken verificationToken = VerificationToken.builder()
                .token(null)
                .build();
        when(userService.getVerificationToken(isNull())).thenReturn(verificationToken);
        mockMvc.perform(get(uri)).andExpect(status().isBadRequest());
        verify(userService, times(1))
                .getVerificationToken(anyString());
        assertNull(verificationToken.getToken());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
