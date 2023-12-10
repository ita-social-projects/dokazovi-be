package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.user.UserIpWhitelistDTO;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.UserIpWhitelistEntity;
import com.softserveinc.dokazovi.repositories.UserIpWhitelistRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.CheckAuthorityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserIpWhitelistServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserIpWhitelistRepository userIpWhitelistRepository;
    @Mock
    private CheckAuthorityService checkAuthorityService;

    @InjectMocks
    private UserIpWhitelistServiceImpl userIpWhitelistService;

    @Test
    public void testUpdateUserIpWhitelist_Successful() {
        UserPrincipal mockUserPrincipal = mock(UserPrincipal.class);
        when(checkAuthorityService.checkAuthority(mockUserPrincipal, "EDIT_AUTHOR")).thenReturn(true);

        UserIpWhitelistDTO mockDTO = new UserIpWhitelistDTO();
        mockDTO.setId(1);
        mockDTO.setWhitelistIps(Arrays.asList("127.0.0.1", "192.168.1.1"));

        UserEntity mockUser = new UserEntity();
        mockUser.setId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));

        when(userIpWhitelistRepository.findAllByUser(mockUser)).thenReturn(new ArrayList<>());

        userIpWhitelistService.updateUserIpWhitelist(mockUserPrincipal, mockDTO);

        verify(userIpWhitelistRepository, times(2)).save(any(UserIpWhitelistEntity.class));
    }

    @Test
    public void testIsIpNotWhitelisted() {
        UserEntity mockUser = new UserEntity();
        mockUser.setId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));
        when(userIpWhitelistRepository.existsByUserAndWhitelistIp(mockUser, "192.168.1.1")).thenReturn(false);

        boolean isWhitelisted = userIpWhitelistService.isIpWhitelisted(1, "192.168.1.1");

        Assertions.assertFalse(isWhitelisted);
    }

    @Test
    public void testUpdateUserIpWhitelist_ExceedsIpLimit() {
        UserPrincipal mockUserPrincipal = mock(UserPrincipal.class);
        when(checkAuthorityService.checkAuthority(mockUserPrincipal, "EDIT_AUTHOR")).thenReturn(true);

        UserIpWhitelistDTO mockDTO = new UserIpWhitelistDTO();
        mockDTO.setId(1);
        List<String> ips = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            ips.add("IP" + i);
        }
        mockDTO.setWhitelistIps(ips);

        assertThrows(IllegalArgumentException.class, () ->
                userIpWhitelistService.updateUserIpWhitelist(mockUserPrincipal, mockDTO));
    }

}
