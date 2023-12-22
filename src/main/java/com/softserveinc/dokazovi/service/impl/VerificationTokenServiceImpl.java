package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.VerificationToken;
import com.softserveinc.dokazovi.repositories.VerificationTokenRepository;
import com.softserveinc.dokazovi.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken createVerificationTokenForUser(UserEntity user, String token) {
        VerificationToken myToken = VerificationToken.builder()
                .token(token)
                .user(user)
                .dateExpiration(LocalDateTime.now().plusMinutes(VerificationToken.EXPIRATION))
                .build();
        return verificationTokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getByToken(String token) {
        return verificationTokenRepository.findByToken(token).orElse(null);
    }

    @Override
    public boolean validateVerificationToken(String token) {
        VerificationToken verificationToken = getByToken(token);
        return isAvailable(verificationToken) && !isExpired(verificationToken);
    }


    @Override
    public void delete(VerificationToken verificationToken) {
        if (isAvailable(verificationToken)) {
            verificationTokenRepository.delete(verificationToken);
        }
    }

    @Override
    public void deleteExpiredTokens() {
        LocalDateTime currentTime = LocalDateTime.now();
        List<VerificationToken> expiredTokens = verificationTokenRepository.findAllExpritedTokens(currentTime);
        verificationTokenRepository.deleteAll(expiredTokens);
    }

    private boolean isAvailable(VerificationToken verificationToken) {
        return verificationToken != null;
    }

    private boolean isExpired(VerificationToken verificationToken) {
        return verificationToken.getDateExpiration().isBefore(LocalDateTime.now());
    }
}
