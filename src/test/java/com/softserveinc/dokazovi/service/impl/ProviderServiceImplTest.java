package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.ProviderEntity;
import com.softserveinc.dokazovi.entity.RoleEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.AuthProvider;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.repositories.ProviderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProviderServiceImplTest {

    @Mock
    ProviderRepository providerRepository;

    @InjectMocks
    ProviderServiceImpl providerService;

    @Test
    void createLocalProviderEntityForUser() {
        String email = "test@mail.com";
        RoleEntity roleEntity = RoleEntity.builder().id(1).name("ROLE_DOCTOR").build();
        UserEntity userEntity = UserEntity.builder()
                .id(1)
                .status(UserStatus.ACTIVE)
                .email(email)
                .role(roleEntity)
                .enabled(true)
                .build();

        ProviderEntity expectedEntity = ProviderEntity.builder()
                .user(userEntity)
                .email(email)
                .userIdByProvider(userEntity.getId().toString())
                .name(AuthProvider.LOCAL.toString())
                .build();

        when(providerRepository.save(any(ProviderEntity.class))).thenReturn(expectedEntity);
        Optional<ProviderEntity> actualEntity = providerService.createLocalProviderEntityForUser(userEntity,email);
        actualEntity.ifPresent(providerEntity -> assertEquals(expectedEntity, providerEntity));

    }

    @Test
    void existsByLocalEmail() {
        when(providerRepository.existsByEmailAndName(any(String.class), any(String.class))).thenReturn(true);
        assertTrue(providerService.existsByLocalEmail("test@test.com"));

    }
}
