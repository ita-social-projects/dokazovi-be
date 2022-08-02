package com.softserveinc.dokazovi.security.oauth2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

import javax.servlet.http.Cookie;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HttpCookieOAuth2AuthorizationRequestRepositoryTest {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

	@BeforeEach
	void init(){
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		httpCookieOAuth2AuthorizationRequestRepository =
				new HttpCookieOAuth2AuthorizationRequestRepository();
	}

	@Test
	void loadAuthorizationRequest() {
		OAuth2AuthorizationRequest authorizationRequest =
				OAuth2AuthorizationRequest.authorizationCode().clientId("id").
						authorizationUri("test").build();

		httpCookieOAuth2AuthorizationRequestRepository.saveAuthorizationRequest(authorizationRequest, request, response);

		Cookie cookie = response.getCookie(HttpCookieOAuth2AuthorizationRequestRepository.OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
		request.setCookies(cookie);

		OAuth2AuthorizationRequest retrievedOauth = httpCookieOAuth2AuthorizationRequestRepository.loadAuthorizationRequest(request);
		String clientId = retrievedOauth.getClientId();

		assertEquals("id", clientId);
	}

	@Test
	void saveAuthorizationRequest(){
		Cookie cookie = new Cookie(HttpCookieOAuth2AuthorizationRequestRepository.OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME, "test");
		request.setCookies(cookie);
		httpCookieOAuth2AuthorizationRequestRepository.saveAuthorizationRequest(null, request, response);

		Cookie[] cookies = response.getCookies();

		assertAll(
				()->assertEquals(1, cookies.length),
				()->assertEquals(0, cookies[0].getMaxAge()),
				()->assertTrue(cookies[0].getValue().isEmpty())
		);
	}
}