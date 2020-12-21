package com.softserveinc.dokazovi.security;

import com.softserveinc.dokazovi.config.AppProperties;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TokenProviderTest {

	@Mock
	private Authentication authentication;
	@Mock
	private AppProperties appProperties;
	@InjectMocks
	private TokenProvider tokenProvider;

	@BeforeEach()
	public void init() {
		authentication = new Authentication() {
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return null;
			}

			@Override
			public Object getCredentials() {
				return null;
			}

			@Override
			public Object getDetails() {
				return null;
			}

			@Override
			public Object getPrincipal() {
				return new UserPrincipal(28, "test@test.com", "test", new HashSet<>());
			}

			@Override
			public boolean isAuthenticated() {
				return false;
			}

			@Override
			public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

			}

			@Override
			public String getName() {
				return null;
			}
		};
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
