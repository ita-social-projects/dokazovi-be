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

	private final PasswordResetTokenRepository passwordResetTokenRepository;

	/**
	 * Gets the password reset token received from passwordResetTokenRepository.
	 *
	 * @param user user received from repository by email
	 * @param token token is generated by controller method
	 */

	@Override
	public PasswordResetTokenEntity createPasswordResetTokenForUser(UserEntity user, String token) {
		PasswordResetTokenEntity myToken = PasswordResetTokenEntity.builder()
				.token(token)
				.userEntity(user)
				.dateExpiration(LocalDateTime.now().plusMinutes(PasswordResetTokenEntity.EXPIRATION))
				.build();
		return passwordResetTokenRepository.save(myToken);
	}

	@Override
	public PasswordResetTokenEntity getByToken(String token) {
		return passwordResetTokenRepository.findByToken(token).orElse(null);
	}

	@Override
	public boolean validatePasswordResetToken(String token) {
		PasswordResetTokenEntity passwordResetTokenEntity =
				passwordResetTokenRepository.findByToken(token).orElse(null);
		return isAvailable(passwordResetTokenEntity) && (!isExpired(passwordResetTokenEntity));
	}

	@Override
	public void delete(PasswordResetTokenEntity passwordResetTokenEntity) {
		if (isAvailable(passwordResetTokenEntity)) {
			passwordResetTokenRepository.delete(passwordResetTokenEntity);
		}
	}

	private boolean isAvailable(PasswordResetTokenEntity passwordResetTokenEntity) {
		return passwordResetTokenEntity != null;
	}

	private boolean isExpired(PasswordResetTokenEntity passwordResetTokenEntity) {
		return passwordResetTokenEntity.getDateExpiration().isBefore(LocalDateTime.now());
	}

}
