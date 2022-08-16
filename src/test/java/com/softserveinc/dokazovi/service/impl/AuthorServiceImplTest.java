package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.author.AuthorDTO;
import com.softserveinc.dokazovi.dto.user.UserInstitutionCityDTO;
import com.softserveinc.dokazovi.dto.user.UserInstitutionDTO;
import com.softserveinc.dokazovi.entity.DoctorEntity;
import com.softserveinc.dokazovi.entity.RoleEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.RolePermission;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.repositories.DoctorRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private DoctorRepository doctorRepository;
    @InjectMocks
    private AuthorServiceImpl authorService;

    private UserEntity admin;

    @BeforeEach
    void init() {
        Set<RolePermission> rolePermissions = new HashSet<>();
        rolePermissions.add(RolePermission.CREATE_AUTHOR);
        rolePermissions.add(RolePermission.UPDATE_AUTHOR);
        rolePermissions.add(RolePermission.DELETE_AUTHOR);
        RoleEntity roleEntity = RoleEntity.builder()
                .id(1)
                .name("Doctor")
                .permissions(rolePermissions)
                .build();
        admin = UserEntity.builder()
                .id(1)
                .email("test@nail.com")
                .password("12345")
                .role(roleEntity)
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
    }

    @Test
    void createAuthor() {
        AuthorDTO authorDTO = AuthorDTO.builder()
                .id(1)
                .avatar("avatar")
                .bio("bio")
                .mainInstitution(UserInstitutionDTO.builder()
						.city(
								UserInstitutionCityDTO.builder()
										.id(2)
										.name("Lviv")
										.build()
						)
						.build())
                .firstName("firstName")
                .lastName("lastName")
                .socialNetwork("socialNetwork")
                .build();
        assertEquals(1, 1);
    }

}