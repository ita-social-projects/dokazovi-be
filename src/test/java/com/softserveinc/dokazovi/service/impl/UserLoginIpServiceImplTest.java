package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.UserLoginIpEntity;
import com.softserveinc.dokazovi.exception.EntityNotFoundException;
import com.softserveinc.dokazovi.repositories.UserLoginIpRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.CheckAuthorityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserLoginIpServiceImplTest {

    @Mock
    private UserLoginIpRepository userLoginIpRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CheckAuthorityService checkAuthorityService;

    @InjectMocks
    private UserLoginIpServiceImpl userLoginIpService;

    @Test
    void testSaveUserIP_NewIP() {
        Integer userId = 1;
        String ipAddress = "127.0.0.1";
        UserEntity mockUser = new UserEntity();
        mockUser.setId(userId);

        when(userLoginIpRepository.existsByUserIdAndIpAddress(userId, ipAddress)).thenReturn(false);
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        userLoginIpService.saveUserIP(userId, ipAddress);

        verify(userLoginIpRepository).save(any(UserLoginIpEntity.class));
    }

    @Test
    void testSaveUserIP_ExistingIP() {
        Integer userId = 1;
        String ipAddress = "127.0.0.1";

        when(userLoginIpRepository.existsByUserIdAndIpAddress(userId, ipAddress)).thenReturn(true);

        userLoginIpService.saveUserIP(userId, ipAddress);

        verify(userLoginIpRepository, never()).save(any(UserLoginIpEntity.class));
    }

    @Test
    void testSaveUserIP_UserNotFound() {
        Integer userId = 1;
        String ipAddress = "127.0.0.1";

        when(userLoginIpRepository.existsByUserIdAndIpAddress(userId, ipAddress)).thenReturn(false);
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                userLoginIpService.saveUserIP(userId, ipAddress));
    }

    @Test
    void testGetAllUserIps_WithAuthority() {
        UserPrincipal mockUserPrincipal = mock(UserPrincipal.class);
        Integer userId = 1;

        when(checkAuthorityService.checkAuthority(mockUserPrincipal, "EDIT_AUTHOR")).thenReturn(true);
        when(userLoginIpRepository.findAllByUserId(userId)).thenReturn(Collections.emptyList());

        List<String> result = userLoginIpService.getAllUserIps(mockUserPrincipal, userId);

        assertTrue(result.isEmpty());
    }

}
