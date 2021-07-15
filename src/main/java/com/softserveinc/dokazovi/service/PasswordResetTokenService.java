package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.entity.UserEntity;

public interface PasswordResetTokenService {

	void createPasswordResetTokenForUser(UserEntity user, String token);

}
