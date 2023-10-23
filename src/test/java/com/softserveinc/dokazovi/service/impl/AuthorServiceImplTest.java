package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.author.AuthorRequestDTO;
import com.softserveinc.dokazovi.entity.AuthorEntity;
import com.softserveinc.dokazovi.entity.CityEntity;
import com.softserveinc.dokazovi.entity.InstitutionEntity;
import com.softserveinc.dokazovi.entity.RoleEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.RolePermission;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.exception.ForbiddenPermissionsException;
import com.softserveinc.dokazovi.repositories.AuthorRepository;
import com.softserveinc.dokazovi.repositories.CityRepository;
import com.softserveinc.dokazovi.repositories.InstitutionRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Ignore
@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private CityRepository cityRepository;
    @Mock
    private InstitutionRepository institutionRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private AuthorServiceImpl authorService;
    @Captor
    private ArgumentCaptor<UserEntity> userEntityArgumentCaptor;
    @Captor
    private ArgumentCaptor<AuthorEntity> authorEntityArgumentCaptor;

    private UserEntity admin;
    private AuthorRequestDTO authorRequestDTO;
    private UserEntity userEntity;
    private RoleEntity adminRole;
    private AuthorEntity authorEntity;
    private InstitutionEntity institutionEntity;
    private Set<RolePermission> wrongPermission;
    private CityEntity cityEntity;

    @BeforeEach
    void init() {
        Set<RolePermission> adminPermision = new HashSet<>();
        adminPermision.add(RolePermission.EDIT_AUTHOR);

        wrongPermission = new HashSet<>();
        adminPermision.add(RolePermission.SAVE_OWN_PUBLICATION);

        adminRole = RoleEntity.builder()
                .id(1)
                .name("Doctor")
                .permissions(adminPermision)
                .build();

        admin = UserEntity.builder()
                .id(1)
                .email("test@nail.com")
                .password("12345")
                .role(adminRole)
                .firstName("test")
                .lastName("test")
                .avatar("test")
                .status(UserStatus.ACTIVE)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .author(new AuthorEntity())
                .phone("test")
                .userProviderEntities(new HashSet<>())
                .enabled(true)
                .build();

        institutionEntity = InstitutionEntity.builder()
                .build();

        userEntity = UserEntity.builder()
                .id(2)
                .email("mail@mail.com")
                .password("password")
                .firstName("firstName")
                .lastName("lastName")
                .avatar("avatar link")
                .status(UserStatus.ACTIVE)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .author(new AuthorEntity())
                .phone("test")
                .userProviderEntities(new HashSet<>())
                .enabled(true)
                .build();

        authorRequestDTO = AuthorRequestDTO.builder()
                .firstName("firstName")
                .lastName("lastName")
                .cityId(190)
                .avatar("avatar link")
                .mainWorkingPlace("Hospital")
                .bio("some text")
                .socialNetworks(new HashSet<>())
                .build();

        authorEntity = AuthorEntity.builder()
                .id(1)
                .profile(userEntity)
                .mainInstitution(institutionEntity)
                .build();

        cityEntity = CityEntity.builder()
                .id(190)
                .build();
    }

    @Test
    void updateWithoutPermission() {
        adminRole.setPermissions(wrongPermission);
        UserPrincipal userPrincipal = UserPrincipal.builder()
                .role(adminRole)
                .build();

        assertThatThrownBy(() -> authorService.update(1, authorRequestDTO, userPrincipal))
                .isInstanceOf(ForbiddenPermissionsException.class);
    }

    @Test
    void update() {
        when(authorRepository.findById(anyInt())).thenReturn(Optional.of(authorEntity));
        when(userRepository.getOne(anyInt())).thenReturn(userEntity);
        when(cityRepository.findById(anyInt())).thenReturn(Optional.of(cityEntity));

        UserPrincipal userPrincipal = UserPrincipal.builder()
                .role(adminRole)
                .build();

        authorService.update(1, authorRequestDTO, userPrincipal);

        verify(authorRepository).save(authorEntityArgumentCaptor.capture());
        Assertions.assertEquals(authorEntityArgumentCaptor.getValue().getId(), authorEntity.getId());
    }

    @Test
    void saveWithoutPermission() {
        adminRole.setPermissions(wrongPermission);
        UserPrincipal userPrincipal = UserPrincipal.builder()
                .role(adminRole)
                .build();

        assertThatThrownBy(() -> authorService.save(authorRequestDTO, userPrincipal))
                .isInstanceOf(ForbiddenPermissionsException.class);
    }

    @Test
    void save() {


        when(cityRepository.findById(anyInt())).thenReturn(Optional.of(cityEntity));

        UserPrincipal userPrincipal = UserPrincipal.builder()
                .role(adminRole)
                .build();

        authorService.save(authorRequestDTO, userPrincipal);

        UserEntity user = UserEntity.builder()
                .firstName(authorRequestDTO.getFirstName())
                .lastName(authorRequestDTO.getLastName())
                .enabled(false)
                .status(UserStatus.NEW)
                .publicEmail(authorRequestDTO.getPublicEmail())
                .socialNetworks(authorRequestDTO.getSocialNetworks())
                .createdAt(userEntity.getCreatedAt())
                .avatar(authorRequestDTO.getAvatar())
                .socialNetworks(authorRequestDTO.getSocialNetworks())
                .build();
        user.setCreatedAt(userEntityArgumentCaptor.getValue().getCreatedAt());
        AuthorEntity author = AuthorEntity.builder()
                .publishedPosts(0L)
                .promotionScale(1.0)
                .mainWorkingPlace(authorRequestDTO.getMainWorkingPlace())
                .city(cityEntity)
                .profile(user)
                .bio(authorRequestDTO.getBio())
                .build();
        verify(userRepository).save(userEntityArgumentCaptor.capture());
        verify(authorRepository).save(authorEntityArgumentCaptor.capture());
        Assertions.assertEquals(authorEntityArgumentCaptor.getValue(), author);
        Assertions.assertEquals(userEntityArgumentCaptor.getValue(), user);
    }

    @Test
    void delete() {
        UserPrincipal userPrincipal = UserPrincipal.builder()
                .role(adminRole)
                .build();

        when(authorRepository.findById(anyInt())).thenReturn(Optional.of(authorEntity));

        authorService.delete(anyInt(), userPrincipal);

        verify(authorRepository, times(1)).delete(any(AuthorEntity.class));
    }

    @Test
    void deleteWithWrongPermission() {
        adminRole.setPermissions(wrongPermission);
        UserPrincipal userPrincipal = UserPrincipal.builder()
                .role(adminRole)
                .build();

        assertThatThrownBy(() -> authorService.delete(1, userPrincipal))
                .isInstanceOf(ForbiddenPermissionsException.class);
    }
}