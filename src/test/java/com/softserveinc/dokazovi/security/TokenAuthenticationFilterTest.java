package com.softserveinc.dokazovi.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenAuthenticationFilterTest {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@Mock
	FilterChain chain;
	@InjectMocks
	TokenAuthenticationFilter tokenAuthenticationFilter;

	@Test
	void doFilterInternal() throws ServletException, IOException {
		tokenAuthenticationFilter.doFilterInternal(request, response, chain);
	}

	@Test
	void getJwtFromRequest() {
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyOCIsImlhdCI6"
				+ "MTYwODU3Mzg3NywiZXhwIjoxNjA5NDM3ODc3fQ.5cbYB3lXDOJmh546wDrZlBwWtNQrtg"
				+ "ElNBOc0M7Hi3Y3ZkHx5bHnZYKGBbZdVeURqBMVWipAFx1fBwtSh-y0OQ";
		when(request.getHeader("Authorization")).thenReturn("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyOCIsImlhdCI6MT"
				+ "YwODU3Mzg3NywiZXhwIjoxNjA5NDM3ODc3fQ.5cbYB3lXDOJmh546wDrZlBw"
				+ "WtNQrtgElNBOc0M7Hi3Y3ZkHx5bHnZYKGBbZdVeURqBMVWipAFx1fBwtSh-y0OQ");
		String actualJwt = tokenAuthenticationFilter.getJwtFromRequest(request);
		assertEquals(token, actualJwt);
	}
}
