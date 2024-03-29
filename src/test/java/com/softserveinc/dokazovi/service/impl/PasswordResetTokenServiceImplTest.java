package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.PasswordResetTokenEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.repositories.PasswordResetTokenRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

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
                .dateExpiration(LocalDateTime.now().plusMinutes(60))
                .build();
    }

    @Test
    void createPasswordResetTokenForUserTest () {
        when(passwordResetTokenRepository.save(any(PasswordResetTokenEntity.class))).thenReturn(expected);
        PasswordResetTokenEntity actual = passwordResetTokenService.createPasswordResetTokenForUser(user, token);
        verify(passwordResetTokenRepository).save(any(PasswordResetTokenEntity.class));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getPasswordResetTokenByTokenTest() {
        when(passwordResetTokenRepository.findByToken(token)).thenReturn(Optional.of(expected));
        PasswordResetTokenEntity actual = passwordResetTokenService.getByToken(token);
        verify(passwordResetTokenRepository).findByToken(token);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deletePasswordResetTokenTest() {
        when(passwordResetTokenRepository.findByToken(token)).thenReturn(Optional.of(expected));
        PasswordResetTokenEntity passwordResetTokenEntity = passwordResetTokenService.getByToken(token);
        passwordResetTokenService.delete(passwordResetTokenEntity);
        verify(passwordResetTokenRepository, times(1)).delete(passwordResetTokenEntity);
    }

    @Test
    void validatePasswordResetTokenTest_isOk() {
        when(passwordResetTokenRepository.findByToken(token)).thenReturn(Optional.of(expected));
        Assertions.assertEquals(true, passwordResetTokenService.validatePasswordResetToken(token));
    }
}
