package com.softserveinc.dokazovi.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RestAuthenticationEntryPointTest {

	@Mock
	private HttpServletRequest httpServletRequest;
	@Mock
	private HttpServletResponse httpServletResponse;
	@Mock
	private AuthenticationException exception;

	@Test
	void commence() throws IOException {
		RestAuthenticationEntryPoint target = new RestAuthenticationEntryPoint();
		target.commence(httpServletRequest, httpServletResponse, exception);
		verify(httpServletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getLocalizedMessage());
	}
}