package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.author.AuthorDTO;
import com.softserveinc.dokazovi.entity.DoctorEntity;
import com.softserveinc.dokazovi.entity.InstitutionEntity;
import com.softserveinc.dokazovi.entity.RoleEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.RolePermission;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.exception.ForbiddenPermissionsException;
import com.softserveinc.dokazovi.repositories.DoctorRepository;
import com.softserveinc.dokazovi.repositories.InstitutionRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private InstitutionRepository institutionRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private AuthorServiceImpl authorService;

    private UserEntity admin;
    private AuthorDTO authorDTO;
    private UserEntity userEntity;
    private RoleEntity adminRole;
    private DoctorEntity doctorEntity;
    private InstitutionEntity institutionEntity;
    private Set<RolePermission> wrongPermission;

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
                .doctor(new DoctorEntity())
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
                .doctor(new DoctorEntity())
                .phone("test")
                .userProviderEntities(new HashSet<>())
                .enabled(true)
                .build();

        authorDTO = AuthorDTO.builder()
                .authorId(2)
                .email("mail@mail.com")
                .password("password")
                .firstName("firstName")
                .lastName("lastName")
                .qualification("super doctor")
                .mainInstitutionId(1)
                .avatar("avatar link")
                .bio("some text")
                .socialNetwork(new HashSet<>())
                .build();

        doctorEntity = DoctorEntity.builder()
                .profile(userEntity)
                .mainInstitution(institutionEntity)
                .build();
    }

    @Test
    void saveAuthorDTOWithId() {
        when(userRepository.findByEmail(authorDTO.getEmail())).thenReturn(Optional.of(admin));
        when(doctorRepository.findById(anyInt())).thenReturn(Optional.of(doctorEntity));
        when(institutionRepository.getOne(anyInt())).thenReturn(institutionEntity);

        authorService.save(authorDTO, UserPrincipal.builder().role(adminRole).build());

        verify(userRepository).save(any(UserEntity.class));
        verify(doctorRepository).save(any(DoctorEntity.class));
    }

    @Test
    void saveAuthorDTOWithoutId() {
        authorDTO.setAuthorId(null);

        when(userRepository.findByEmail(authorDTO.getEmail())).thenReturn(Optional.empty());
        when(doctorRepository.save(any(DoctorEntity.class))).thenReturn(doctorEntity);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(passwordEncoder.encode(anyString())).thenReturn(anyString());

        authorService.save(authorDTO, UserPrincipal.builder().role(adminRole).build());

        verify(userRepository).save(any(UserEntity.class));
        verify(doctorRepository).save(any(DoctorEntity.class));
    }

    @Test
    void saveAuthorDTOWithoutPermission() {
        adminRole.setPermissions(wrongPermission);
        UserPrincipal userPrincipal = UserPrincipal.builder()
                        .role(adminRole)
                        .build();

        when(userRepository.findByEmail(authorDTO.getEmail())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authorService.save(authorDTO, userPrincipal))
                .isInstanceOf(ForbiddenPermissionsException.class);
    }

    @Test
    void updateWhenIdIsNull() {
        authorDTO.setAuthorId(null);

        UserPrincipal userPrincipal = UserPrincipal.builder()
                .role(adminRole)
                .build();

        assertThatThrownBy(() -> authorService.update(authorDTO, userPrincipal))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void updateWhenDoesntEnoughPermission() {
        adminRole.setPermissions(wrongPermission);
        UserPrincipal userPrincipal = UserPrincipal.builder()
                .role(adminRole)
                .build();

        assertThatThrownBy(() -> authorService.update(authorDTO, userPrincipal))
                .isInstanceOf(ForbiddenPermissionsException.class);
    }

    @Test
    void updateWhenDoctorDoesntExist() {
        UserPrincipal userPrincipal = UserPrincipal.builder()
                .role(adminRole)
                .build();

        when(doctorRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authorService.update(authorDTO, userPrincipal))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void delete() {
        UserPrincipal userPrincipal = UserPrincipal.builder()
                .role(adminRole)
                .build();

        when(doctorRepository.getOne(anyInt())).thenReturn(doctorEntity);

        authorService.delete(anyInt(), userPrincipal);

        verify(doctorRepository, times(1)).deleteById(anyInt());
        verify(userRepository, times(1)).deleteById(anyInt());
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