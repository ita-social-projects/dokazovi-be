package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.entity.PasswordResetTokenEntity;
import com.softserveinc.dokazovi.entity.UserEntity;

public interface PasswordResetTokenService {

	PasswordResetTokenEntity createPasswordResetTokenForUser(UserEntity user, String token);

	PasswordResetTokenEntity getByToken(String token);

	boolean validatePasswordResetToken(String token);

	void delete (PasswordResetTokenEntity passwordResetTokenEntity);

}
