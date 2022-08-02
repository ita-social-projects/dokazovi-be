package com.softserveinc.dokazovi.security.oauth2;

import com.softserveinc.dokazovi.config.AppProperties;
import com.softserveinc.dokazovi.dto.user.UserDTO;
import com.softserveinc.dokazovi.entity.DoctorEntity;
import com.softserveinc.dokazovi.entity.RoleEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.security.TokenProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OAuth2AuthenticationSuccessHandlerTest {

	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private TokenProvider tokenProvider;
	@Mock
	private AppProperties appProperties;
	@Mock
	private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

	private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandlerUnderTest;
	private UserEntity user;
	private UserDTO userDTO;

	@BeforeEach
	void init() {
		oAuth2AuthenticationSuccessHandlerUnderTest = new OAuth2AuthenticationSuccessHandler(
				tokenProvider, appProperties, httpCookieOAuth2AuthorizationRequestRepository);

		user = UserEntity.builder()
				.id(1)
				.firstName("Admin")
				.lastName("Admin")
				.email("admin@mail.com")
				.password("password")
				.phone("+380951111111")
				.avatar("imageUrl")
				.status(UserStatus.NEW)
				.role(RoleEntity.builder().id(1).name("admin").build())
				.doctor(DoctorEntity.builder().id(1).qualification("surgeon").build())
				.createdAt(new Timestamp(System.currentTimeMillis()))
				.enabled(false)
				.build();

		userDTO = UserDTO.builder()
				.id(1)
				.firstName("Admin")
				.lastName("Admin")
				.email("admin@mail.com")
				.phone("+380951111111")
				.avatar("imageUrl")
				.build();
	}

	@Test
	void onAuthenticationSuccess() throws Exception {
		final Authentication authentication = new UsernamePasswordAuthenticationToken(userDTO, null);

		when(tokenProvider.createToken(authentication)).thenReturn("result");

		oAuth2AuthenticationSuccessHandlerUnderTest.onAuthenticationSuccess(request, response, authentication);

		verify(httpCookieOAuth2AuthorizationRequestRepository).removeAuthorizationRequestCookies(any(HttpServletRequest.class),
				any(HttpServletResponse.class));
	}

	@Test
	void determineTargetUrl() {
		final Authentication authentication = new UsernamePasswordAuthenticationToken(userDTO, null);

		when(tokenProvider.createToken(authentication)).thenReturn("result");

		// Run the test
		final String result = oAuth2AuthenticationSuccessHandlerUnderTest.determineTargetUrl(request, response, authentication);


		Assertions.assertEquals("/?token=result", result);
	}

	@Test
	void clearAuthenticationAttributes() {
		oAuth2AuthenticationSuccessHandlerUnderTest.clearAuthenticationAttributes(request, response);

		verify(httpCookieOAuth2AuthorizationRequestRepository).removeAuthorizationRequestCookies(any(HttpServletRequest.class), any(HttpServletResponse.class));
	}
}