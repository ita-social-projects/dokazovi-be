package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.foreignexpert.ForeignExpertDTO;
import com.softserveinc.dokazovi.dto.foreignexpert.ForeignExpertSaveDTO;
import com.softserveinc.dokazovi.dto.foreignexpert.ForeignExpertSearchResultDTO;
import com.softserveinc.dokazovi.entity.ForeignExpertEntity;
import com.softserveinc.dokazovi.entity.RoleEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.RolePermission;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.mapper.ForeignExpertMapper;
import com.softserveinc.dokazovi.repositories.ForeignExpertRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ForeignExpertServiceImplTest {

	@Mock
	private ForeignExpertMapper foreignExpertMapper;

	@Mock
	private ForeignExpertRepository foreignExpertRepository;

	@InjectMocks
	private ForeignExpertServiceImpl foreignExpertService;


	static final String FE_SEARCH_QUERY_JOHN = "John";

	static final Integer FE_ID = 1;
	static final String FE_FULL_NAME = "John Doe";
	static final String FE_BIO = "Test bio";
	static final String FE_AVATAR = "IRL we should have this filled";

	ForeignExpertEntity foreignExpertEntity;
	ForeignExpertDTO foreignExpertDTO;
	ForeignExpertSearchResultDTO foreignExpertSearchResultDTO;
	ForeignExpertSaveDTO foreignExpertSaveDTO;
	Page<ForeignExpertEntity> foreignExpertPage;

	UserPrincipal adminUserPrincipal;

	@BeforeEach
	void beforeEach() {
		foreignExpertDTO = ForeignExpertDTO.builder()
				.id(FE_ID)
				.fullName(FE_FULL_NAME)
				.bio(FE_BIO)
				.avatar(FE_AVATAR)
				.build();

		foreignExpertSearchResultDTO = ForeignExpertSearchResultDTO.builder()
				.id(FE_ID)
				.fullName(FE_FULL_NAME)
				.avatar(FE_AVATAR)
				.build();

		foreignExpertSaveDTO = ForeignExpertSaveDTO.builder()
				.id(FE_ID)
				.fullName(FE_FULL_NAME)
				.bio(FE_BIO)
				.avatar(FE_AVATAR)
				.build();

		foreignExpertEntity = ForeignExpertEntity.builder()
				.id(FE_ID)
				.fullName(FE_FULL_NAME)
				.bio(FE_BIO)
				.avatar(FE_AVATAR)
				.build();
		foreignExpertPage = new PageImpl<>(List.of(foreignExpertEntity));

		// Configure admin user for testing
		Set<RolePermission> rolePermissions = new HashSet<>();
		rolePermissions.add(RolePermission.SAVE_FOREIGN_EXPERT);
		RoleEntity adminRoleEntity = RoleEntity.builder()
				.id(1)
				.name("Administrator")
				.permissions(rolePermissions)
				.build();
		UserEntity adminAuthor = UserEntity.builder()
				.id(1)
				.email("test@nail.com")
				.password("12345")
				.role(adminRoleEntity)
				.firstName("test")
				.lastName("test")
				.avatar("test")
				.status(UserStatus.ACTIVE)
				.createdAt(Timestamp.valueOf(LocalDateTime.now()))
				.doctor(null)
				.phone("test")
				.userProviderEntities(new HashSet<>())
				.enabled(true)
				.build();
		adminUserPrincipal = UserPrincipal.create(adminAuthor);
	}

	@Test
	void search_John_ShouldGiveJohnDoe() {
		Pageable pageable = PageRequest.of(0, 1);
		when(foreignExpertRepository.searchByFullName(anyString(), any(Pageable.class)))
				.thenReturn(foreignExpertPage);

		when(foreignExpertMapper.toForeignExpertSearchResultDTO(any(ForeignExpertEntity.class)))
				.thenReturn(foreignExpertSearchResultDTO);

		Page<ForeignExpertSearchResultDTO> dtoPage = foreignExpertService.search(FE_SEARCH_QUERY_JOHN, pageable);

		Optional<ForeignExpertSearchResultDTO> jd = dtoPage
				.stream()
				.findFirst();

		Assertions.assertTrue(jd.isPresent(),
				"Searching 'John' foreign expert in ['John Doe'] should give at least one element");

		Assertions.assertEquals(FE_FULL_NAME, jd.get().getFullName(),
				"Searching 'John' foreign expert in ['John Doe'] should give 'John Doe'");

		verify(foreignExpertMapper, times(1))
				.toForeignExpertSearchResultDTO(any(ForeignExpertEntity.class));
		verify(foreignExpertRepository, times(1))
				.searchByFullName(anyString(), any(Pageable.class));

		verifyNoMoreInteractions(
				foreignExpertMapper,
				foreignExpertRepository
		);
	}

	@Test
	void save_ForeignExpertIsPresentAndUserIsAdmin_Okay() {
		when(foreignExpertRepository.findById(anyInt()))
				.thenReturn(Optional.of(foreignExpertEntity));
		when(foreignExpertRepository.save(any(ForeignExpertEntity.class)))
				.then(returnsFirstArg());

		when(foreignExpertMapper.toForeignExpertDTO(any(ForeignExpertEntity.class)))
				.thenReturn(foreignExpertDTO);
		when(foreignExpertMapper.updateForeignExpertEntityFromDTO(
				any(ForeignExpertSaveDTO.class),
				any(ForeignExpertEntity.class)))
				.thenReturn(foreignExpertEntity);


		ForeignExpertDTO dto = foreignExpertService.save(foreignExpertSaveDTO, adminUserPrincipal);

		Assertions.assertNotNull(dto, "ForeignExpertService.save should return a non-null value");

		Assertions.assertEquals(FE_ID, dto.getId());
		Assertions.assertEquals(FE_FULL_NAME, dto.getFullName());

		verifyNoMoreInteractions(
				foreignExpertMapper,
				foreignExpertRepository
		);
	}

}
