package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.info.PlatformInformationDTO;
import com.softserveinc.dokazovi.entity.PlatformInformationEntity;
import com.softserveinc.dokazovi.entity.RoleEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.RolePermission;
import com.softserveinc.dokazovi.exception.ForbiddenPermissionsException;
import com.softserveinc.dokazovi.mapper.PlatformInformationMapper;
import com.softserveinc.dokazovi.repositories.PlatformInformationRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlatformInformationServiceImplTest {

	@Mock
	private PlatformInformationRepository platformInformationRepository;

	@Mock
	private PlatformInformationMapper mapper;

	@InjectMocks
	private PlatformInformationServiceImpl infoService;

	private UserEntity adminUserEntity;
	private UserEntity anyUserWithoutAppropriatePermissionsEntity;
	private PlatformInformationEntity infoEntityWithId;
	private PlatformInformationEntity infoEntityWithoutId;
	private PlatformInformationDTO infoDTOWithoutId;

	@BeforeEach
	void init() {
		Set<RolePermission> rolePermissions = new HashSet<>();
		rolePermissions.add(RolePermission.SAVE_PLATFORM_INFORMATION);
		rolePermissions.add(RolePermission.UPDATE_PLATFORM_INFORMATION);
		RoleEntity adminRoleEntity = RoleEntity.builder()
				.id(1)
				.name("Administrator")
				.permissions(rolePermissions)
				.build();
		adminUserEntity = UserEntity.builder()
				.id(1)
				.email("admin@mail.com")
				.password("12345")
				.role(adminRoleEntity)
				.build();

		RoleEntity roleWithoutPermissionsEntity = RoleEntity.builder()
				.id(1)
				.name("Administrator")
				.permissions(new HashSet<>())
				.build();
		anyUserWithoutAppropriatePermissionsEntity = UserEntity.builder()
				.id(1)
				.email("someHuman@mail.com")
				.password("12345")
				.role(roleWithoutPermissionsEntity)
				.build();

		Integer id = 1;
		String title = "Some Title";
		String text = "Some Text";
		infoEntityWithId = PlatformInformationEntity
				.builder()
				.id(id)
				.title(title)
				.text(text)
				.build();
		infoEntityWithoutId = PlatformInformationEntity
				.builder()
				.title(title)
				.text(text)
				.build();
		infoDTOWithoutId = PlatformInformationDTO
				.builder()
				.title(title)
				.text(text)
				.build();
	}

	@Test
	void findPlatformInfoById() {
		Integer id = 1;

		when(platformInformationRepository.findById(any(Integer.class))).thenReturn(Optional.of(infoEntityWithId));

		infoService.getInfoById(id);

		verify(mapper).toPlatformInformationDTO(infoEntityWithId);
	}

	@Test
	void saveInfo_AdminRole_OK() {
		when(mapper.toPlatformInformationEntity(any(PlatformInformationDTO.class))).thenReturn(infoEntityWithoutId);

		UserPrincipal userPrincipal = UserPrincipal.create(adminUserEntity);

		infoService.saveInfo(userPrincipal, infoDTOWithoutId);

		verify(mapper, times(1)).toPlatformInformationDTO(any());
	}

	@Test
	void saveInfo_UserHasNoPermissions_TrowsForbiddenPermissionsException() {
		when(mapper.toPlatformInformationEntity(any(PlatformInformationDTO.class))).thenReturn(infoEntityWithoutId);

		UserPrincipal userPrincipal = UserPrincipal.create(anyUserWithoutAppropriatePermissionsEntity);

		assertThrows(ForbiddenPermissionsException.class, () -> infoService.saveInfo(userPrincipal, infoDTOWithoutId));
	}

	@Test
	void updateInfo_AdminRole_OK() {
		when(mapper.toPlatformInformationEntity(any(PlatformInformationDTO.class))).thenReturn(infoEntityWithoutId);

		UserPrincipal userPrincipal = UserPrincipal.create(adminUserEntity);

		infoService.updateInfo(userPrincipal, infoDTOWithoutId);

		verify(mapper, times(1)).toPlatformInformationDTO(any());
	}

	@Test
	void updateInfo_UserHasNoPermissions_TrowsForbiddenPermissionsException() {
		when(mapper.toPlatformInformationEntity(any(PlatformInformationDTO.class))).thenReturn(infoEntityWithoutId);

		UserPrincipal userPrincipal = UserPrincipal.create(anyUserWithoutAppropriatePermissionsEntity);

		assertThrows(ForbiddenPermissionsException.class,
				() -> infoService.updateInfo(userPrincipal, infoDTOWithoutId));
	}
}
