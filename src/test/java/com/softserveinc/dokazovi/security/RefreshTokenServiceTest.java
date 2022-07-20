package com.softserveinc.dokazovi.security;

import com.softserveinc.dokazovi.config.AppProperties;
import com.softserveinc.dokazovi.dto.payload.RefreshToken;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.exception.TokenRefreshException;
import com.softserveinc.dokazovi.repositories.RefreshTokenRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RefreshTokenServiceTest {

	@Mock
	UserRepository userRepository;
	@Mock
	RefreshTokenRepository refreshTokenRepository;
	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	AppProperties appProperties;
	@Mock
	AppProperties.Auth auth;
	@InjectMocks
	RefreshTokenService refreshTokenService;

	UserEntity user;

	@BeforeEach
	void setUp() {
		user = UserEntity.builder()
				.id(28)
				.enabled(true)
				.build();
	}

	@Test
	void createRefreshToken() {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setUser(user);
		refreshToken.setExpiryDate(Instant.now().plusMillis(60000L));
		when(userRepository.findById(28)).thenReturn(Optional.of(user));
		when(appProperties.getAuth().getRefreshTokenExpirationMsec()).thenReturn(60000L);
		when(refreshTokenRepository.save(any(RefreshToken.class))).thenReturn(refreshToken);
		RefreshToken refreshTokenResult = refreshTokenService.createRefreshToken(user.getId());
		Assertions.assertEquals(refreshToken.getUser().getId(), refreshTokenResult.getUser().getId());
		Assertions.assertEquals(refreshToken.getUser(), refreshTokenResult.getUser());
		Assertions.assertNotNull(refreshTokenResult.getExpiryDate());
	}

	@Test
	void verifyExpirationTrue() {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setId(1);
		refreshToken.setUser(user);
		refreshToken.setExpiryDate(Instant.now().plusMillis(60000L));
		refreshToken.setToken("950c9760-805e-449c-a966-2d0d5ebd86f4");
		RefreshToken refreshTokenResut = refreshTokenService.verifyExpiration(refreshToken);
		Assertions.assertEquals(refreshToken, refreshTokenResut);
	}

	@Test
	void verifyExpirationFalse() {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setId(1);
		refreshToken.setUser(user);
		refreshToken.setExpiryDate(Instant.now().minusMillis(60000L));
		refreshToken.setToken("950c9760-805e-449c-a966-2d0d5ebd86f4");
		assertThrows(TokenRefreshException.class, () -> refreshTokenService.verifyExpiration(refreshToken));
	}


}