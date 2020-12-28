package com.softserveinc.dokazovi.security;

import com.softserveinc.dokazovi.config.AppProperties;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenProviderTest {

	@Mock
	private Authentication authentication;
	@Mock
	private AppProperties appProperties;
	@InjectMocks
	private TokenProvider tokenProvider;

	@BeforeEach()
	public void init() {
		appProperties = new AppProperties();
		appProperties.getAuth().setTokenSecret(secret);
		appProperties.getAuth().setTokenExpirationMsec(864000000);
		tokenProvider = new TokenProvider(appProperties);
	}

	private final Integer expectedId = 28;
	private final String validToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyOCIsImlhdCI6MTYw"
			+ "ODU2Mjk0MCwiZXhwIjoxNjA5NDI2OTQwfQ.nAVfHwasR868Ubo5cOhO2qxJpLWSQD7UJJYjl1p"
			+ "bhUP1tEtyCuYjQBftPrkRkoL0LCMC6Mx16WfTmDmjA_q0vg";
	private final String secret = "926D96C90030DD58429D2751AC1BDBBC";

	@Test
	void createToken() {
		UserPrincipal userPrincipal = new UserPrincipal(28, "test@test.com", "test", new HashSet<>());
		when(authentication.getPrincipal()).thenReturn(userPrincipal);
		String token = tokenProvider.createToken(authentication);
		String actualId = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		assertEquals(expectedId.toString(), actualId);
	}

	@Test
	void getUserIdFromToken() {
		Integer actualId = tokenProvider.getUserIdFromToken(validToken);
		assertEquals(expectedId, actualId);
	}

	@Test
	void validateToken() throws SignatureException, MalformedJwtException, ExpiredJwtException,
			UnsupportedJwtException, IllegalArgumentException {
		tokenProvider.validateToken(validToken);
	}
}