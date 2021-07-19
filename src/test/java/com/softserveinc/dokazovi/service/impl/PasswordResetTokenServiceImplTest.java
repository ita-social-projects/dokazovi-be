package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.PasswordResetTokenEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.repositories.PasswordResetTokenRepository;
import com.softserveinc.dokazovi.service.PasswordResetTokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PasswordResetTokenServiceImplTest {

	@Mock
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@InjectMocks
	private PasswordResetTokenServiceImpl passwordResetTokenService;

	PasswordResetTokenEntity expected;
	private final Long id = 1L;
	private final String token = "ef590bd8-e993-4153-8206-b963732bfeb9";
	private final UserEntity user = UserEntity.builder().id(1).build();

	@BeforeEach
	public void setUp() {
		expected = PasswordResetTokenEntity.builder()
				.id(id)
				.token(token)
				.userEntity(user)
				.dateExpiration(LocalDateTime.now())
				.build();
	}

	@Test
	void createPasswordResetTokenForUserTest () {
		when(passwordResetTokenRepository.save(any(PasswordResetTokenEntity.class))).thenReturn(expected);
		PasswordResetTokenEntity actual = passwordResetTokenService.createPasswordResetTokenForUser(user, token);
		verify(passwordResetTokenRepository).save(any(PasswordResetTokenEntity.class));
		Assertions.assertEquals(expected, actual);
	}

}
