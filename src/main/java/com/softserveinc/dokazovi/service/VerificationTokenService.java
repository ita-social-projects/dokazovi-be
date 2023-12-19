package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.VerificationToken;

public interface VerificationTokenService {
    VerificationToken createVerificationTokenForUser(UserEntity user, String token);

    VerificationToken getByToken(String token);

    boolean validateVerificationToken(String token);

    void delete (VerificationToken passwordResetTokenEntity);

    void deleteExpiredTokens();
}
