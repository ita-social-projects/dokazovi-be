package com.softserveinc.dokazovi.security.oauth2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OAuth2AuthenticationFailureHandlerTest {

    @Mock
    private OAuth2AuthenticationFailureHandler oauth2AuthenticationFailureHandler;
    @Mock
    HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @BeforeEach
    void init() {
        oauth2AuthenticationFailureHandler =
                new OAuth2AuthenticationFailureHandler(httpCookieOAuth2AuthorizationRequestRepository);
    }

    @Test
    void testOnAuthenticationFailure() throws Exception {
        final AuthenticationException exception = new BadCredentialsException("error");

        oauth2AuthenticationFailureHandler.onAuthenticationFailure(request, response, exception);

        verify(httpCookieOAuth2AuthorizationRequestRepository)
                .removeAuthorizationRequestCookies(any(HttpServletRequest.class),
                        any(HttpServletResponse.class));
    }
}