package com.softserveinc.dokazovi.security.oauth2;

import com.softserveinc.dokazovi.config.AppProperties;
import com.softserveinc.dokazovi.dto.user.UserDTO;
import com.softserveinc.dokazovi.security.TokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

	private OAuth2AuthenticationSuccessHandler oauth2AuthenticationSuccessHandlerUnderTest;
	private UserDTO userDTO;

	@BeforeEach
	void init() {
		oauth2AuthenticationSuccessHandlerUnderTest = new OAuth2AuthenticationSuccessHandler(
				tokenProvider, appProperties, httpCookieOAuth2AuthorizationRequestRepository);

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

		oauth2AuthenticationSuccessHandlerUnderTest.onAuthenticationSuccess(request, response, authentication);

		verify(httpCookieOAuth2AuthorizationRequestRepository)
				.removeAuthorizationRequestCookies(any(HttpServletRequest.class),
				any(HttpServletResponse.class));
	}

	@Test
	void determineTargetUrl() {
		final Authentication authentication = new UsernamePasswordAuthenticationToken(userDTO, null);

		when(tokenProvider.createToken(authentication)).thenReturn("result");

		final String result = oauth2AuthenticationSuccessHandlerUnderTest
				.determineTargetUrl(request, response, authentication);

		assertEquals("/?token=result", result);
	}

	@Test
	void clearAuthenticationAttributes() {
		oauth2AuthenticationSuccessHandlerUnderTest.clearAuthenticationAttributes(request, response);

		verify(httpCookieOAuth2AuthorizationRequestRepository)
				.removeAuthorizationRequestCookies(any(HttpServletRequest.class), any(HttpServletResponse.class));
	}
}