package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.PasswordResetTokenEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.repositories.PasswordResetTokenRepository;
import com.softserveinc.dokazovi.service.PasswordResetTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Override
	public void createPasswordResetTokenForUser(UserEntity user, String token) {
		PasswordResetTokenEntity myToken = PasswordResetTokenEntity.builder()
				.token(token)
				.userEntity(user)
				.dateExpiration(LocalDateTime.now().plusMinutes(PasswordResetTokenEntity.EXPIRATION))
				.build();
		passwordResetTokenRepository.save(myToken);
	}
}
