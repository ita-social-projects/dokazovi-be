package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.VerificationToken;
import com.softserveinc.dokazovi.repositories.VerificationTokenRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VerificationTokenServiceImplTest {
    @Mock
    private VerificationTokenRepository verificationTokenRepository;

    @InjectMocks
    private VerificationTokenServiceImpl verificationTokenService;

    VerificationToken expected;
    private final Integer id = 1;
    private final String token = "ef590bd8-e993-4153-8206-b963732bfeb9";
    private final UserEntity user = UserEntity.builder().id(1).build();

    @BeforeEach
    public void setUp() {
        expected = VerificationToken.builder()
                .id(id)
                .token(token)
                .user(user)
                .dateExpiration(LocalDateTime.now().plusMinutes(60))
                .build();
    }

    @Test
    void createPasswordResetTokenForUserTest () {
        when(verificationTokenRepository.save(any(VerificationToken.class))).thenReturn(expected);
        VerificationToken actual = verificationTokenService.createVerificationTokenForUser(user, token);
        verify(verificationTokenRepository).save(any(VerificationToken.class));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getPasswordResetTokenByTokenTest() {
        when(verificationTokenRepository.findByToken(token)).thenReturn(Optional.of(expected));
        VerificationToken actual = verificationTokenService.getByToken(token);
        verify(verificationTokenRepository).findByToken(token);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deletePasswordResetTokenTest() {
        when(verificationTokenRepository.findByToken(token)).thenReturn(Optional.of(expected));
        VerificationToken passwordResetTokenEntity = verificationTokenService.getByToken(token);
        verificationTokenService.delete(passwordResetTokenEntity);
        verify(verificationTokenRepository, times(1)).delete(passwordResetTokenEntity);
    }

    @Test
    void validatePasswordResetTokenTest_isOk() {
        when(verificationTokenRepository.findByToken(token)).thenReturn(Optional.of(expected));
        Assertions.assertEquals(true, verificationTokenService.validateVerificationToken(token));
    }
}
