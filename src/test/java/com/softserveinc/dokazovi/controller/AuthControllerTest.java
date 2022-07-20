package com.softserveinc.dokazovi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.dokazovi.dto.payload.LoginRequest;
import com.softserveinc.dokazovi.dto.payload.RefreshToken;
import com.softserveinc.dokazovi.dto.payload.RefreshTokenRequest;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.security.RefreshTokenService;
import com.softserveinc.dokazovi.security.TokenProvider;
import com.softserveinc.dokazovi.security.UserPrincipal;
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

import java.time.Instant;
import java.util.Optional;

import static com.softserveinc.dokazovi.controller.EndPoints.AUTH;
import static com.softserveinc.dokazovi.controller.EndPoints.AUTH_LOGIN;
import static com.softserveinc.dokazovi.controller.EndPoints.REFRESH_TOKEN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
	RefreshTokenService refreshTokenService;
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

		String refreshTokenString = "4a714dd1-a71d-4a29-9327-e172db25a042";

		String uri = AUTH + AUTH_LOGIN;

		UserEntity user = UserEntity.builder()
				.id(1)
				.email(email)
				.password(password)
				.enabled(true)
				.build();
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setId(1);
		refreshToken.setToken(refreshTokenString);
		refreshToken.setUser(user);
		refreshToken.setExpiryDate(Instant.now().plusMillis(600000L));
		when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
				.thenReturn(authentication);
		when(tokenProvider.createToken(any(Authentication.class))).thenReturn(token);
		when(userService.findByEmail(anyString())).thenReturn(user);
		when(refreshTokenService.createRefreshToken(anyInt())).thenReturn(refreshToken);
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

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void refreshToken() throws Exception {
		String uri = AUTH + REFRESH_TOKEN;
		String token = "950c9760-805e-449c-a966-2d0d5ebd86f4";
		String refreshTokenString = "4a714dd1-a71d-4a29-9327-e172db25a042";
		RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
		refreshTokenRequest.setRefreshToken(refreshTokenString);
		UserEntity user = UserEntity.builder()
				.id(1)
				.enabled(true)
				.build();
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setId(1);
		refreshToken.setToken(refreshTokenString);
		refreshToken.setUser(user);
		when(refreshTokenService.findByToken(anyString())).thenReturn(Optional.of(refreshToken));
		when(refreshTokenService.verifyExpiration(any(RefreshToken.class))).thenReturn(refreshToken);
		when(tokenProvider.createToken(any(UserPrincipal.class))).thenReturn(token);
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
						.content(asJsonString(refreshTokenRequest))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		verify(refreshTokenService, times(1))
				.findByToken(anyString());
	}
}
