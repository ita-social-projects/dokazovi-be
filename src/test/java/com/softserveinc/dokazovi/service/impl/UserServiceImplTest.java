package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.VerificationToken;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.dto.payload.SignUpRequest;
import com.softserveinc.dokazovi.exception.EntityNotFoundException;
import com.softserveinc.dokazovi.mapper.UserMapper;
import com.softserveinc.dokazovi.pojo.UserSearchCriteria;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.repositories.VerificationTokenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@Mock
	PasswordEncoder passwordEncoder;
	@Mock
	private UserRepository userRepository;
	@Mock
	private VerificationTokenRepository tokenRepository;
	@Mock
	private UserMapper userMapper;
	@Mock
	private Pageable pageable;
	@InjectMocks
	private UserServiceImpl userService;


	@Test
	void findExpertById() {
		Integer id = 1;
		UserEntity userEntity = UserEntity.builder()
				.id(id)
				.build();

		when(userRepository.findById(id))
				.thenReturn(Optional.of(userEntity));
		userService.findExpertById(id);

		verify(userMapper).toUserDTO(userEntity);
	}

	@Test
	void getRandomExpertPreview() {
		Page<UserEntity> userEntityPage = new PageImpl<>(List.of(new UserEntity(), new UserEntity()));

		when(userRepository.findRandomExperts(any(Pageable.class)))
				.thenReturn(userEntityPage);
		userService.findRandomExpertPreview(null, pageable);

		verify(userMapper, times(userEntityPage.getNumberOfElements())).toUserDTO(any(UserEntity.class));
	}

	@Test
	void getRandomExpertPreviewByDirections() {
		Page<UserEntity> userEntityPage = new PageImpl<>(List.of(new UserEntity(), new UserEntity()));
		Set<Integer> directionIds = Set.of(1, 2);

		when(userRepository.findRandomExpertsByDirectionsIdIn(anySet(), any(Pageable.class)))
				.thenReturn(userEntityPage);
		userService.findRandomExpertPreview(directionIds, pageable);

		verify(userMapper, times(userEntityPage.getNumberOfElements())).toUserDTO(any(UserEntity.class));
	}

	@Test
	void findAllExperts_NotFiltered() {

		Set<Integer> set = new HashSet<>();
		UserSearchCriteria userSearchCriteria = new UserSearchCriteria();
		userSearchCriteria.setUserNameForTesting("");
		userSearchCriteria.setDirections(set);
		userSearchCriteria.setRegions(set);

		Page<UserEntity> userEntityPage = Page.empty();

		when(userRepository.findDoctorsProfiles(pageable)).thenReturn(userEntityPage);

		assertEquals(userEntityPage, userService.findAllExperts(userSearchCriteria, pageable));

	}

	@Test
	void findAllExperts_ByName() {

		Set<Integer> set = new HashSet<>();
		UserSearchCriteria userSearchCriteria = new UserSearchCriteria();
		userSearchCriteria.setUserName("Ни");
		userSearchCriteria.setDirections(set);
		userSearchCriteria.setRegions(set);

		Page<UserEntity> userEntityPage = Page.empty();

		when(userRepository.findDoctorsByName(userSearchCriteria.getUserName(), pageable)).thenReturn(userEntityPage);

		assertEquals(userEntityPage, userService.findAllExperts(userSearchCriteria, pageable));

	}

	@Test
	void findAllExperts_ByRegions() {

		Set<Integer> setDir = new HashSet<>();
		Set<Integer> setReg = new HashSet<>();
		setReg.add(1);
		UserSearchCriteria userSearchCriteria = new UserSearchCriteria();
		userSearchCriteria.setUserNameForTesting("");
		userSearchCriteria.setDirections(setDir);
		userSearchCriteria.setRegions(setReg);

		Page<UserEntity> userEntityPage = Page.empty();

		when(userRepository.findDoctorsProfilesByRegionsIds(userSearchCriteria.getRegions(), pageable))
				.thenReturn(userEntityPage);

		assertEquals(userEntityPage, userService.findAllExperts(userSearchCriteria, pageable));
	}

	@Test
	void findAllExperts_ByDirections() {

		UserSearchCriteria userSearchCriteria = new UserSearchCriteria();
		userSearchCriteria.setUserNameForTesting("");
		Set<Integer> setDir = new HashSet<>();
		Set<Integer> setReg = new HashSet<>();
		setDir.add(1);
		userSearchCriteria.setDirections(setDir);
		userSearchCriteria.setRegions(setReg);

		Page<UserEntity> userEntityPage = Page.empty();

		when(userRepository.findDoctorsProfilesByDirectionsIds(userSearchCriteria.getDirections(), pageable))
				.thenReturn(userEntityPage);

		assertEquals(userEntityPage, userService.findAllExperts(userSearchCriteria, pageable));
	}

	@Test
	void findAllExperts_ByDirectionsAndRegions() {

		Set<Integer> setDir = new HashSet<>();
		Set<Integer> setReg = new HashSet<>();
		UserSearchCriteria userSearchCriteria = new UserSearchCriteria();
		setDir.add(1);
		setReg.add(1);
		userSearchCriteria.setUserNameForTesting("");
		userSearchCriteria.setDirections(setDir);
		userSearchCriteria.setRegions(setReg);

		Page<UserEntity> userEntityPage = Page.empty();

		when(userRepository.findDoctorsProfiles(userSearchCriteria.getDirections(),
				userSearchCriteria.getRegions(), pageable)).thenReturn(userEntityPage);

		assertEquals(userEntityPage, userService.findAllExperts(userSearchCriteria, pageable));
	}

	@Test
	void findAllExperts_ByOtherConditions() {

		Set<Integer> setDir = new HashSet<>();
		Set<Integer> setReg = new HashSet<>();
		setReg.add(1);
		UserSearchCriteria userSearchCriteria = new UserSearchCriteria();
		userSearchCriteria.setUserName("Ни");
		userSearchCriteria.setDirections(setDir);
		userSearchCriteria.setRegions(setReg);

		assertThrows(EntityNotFoundException.class, () -> userService.findAllExperts(userSearchCriteria, pageable));
	}

	@Test
	void findAllExpertsByDirectionsAndRegions_NotFiltered() {
		Page<UserEntity> userEntityPage = new PageImpl<>(List.of(new UserEntity(), new UserEntity()));

		Set<Integer> set = new HashSet<>();
		UserSearchCriteria userSearchCriteria = new UserSearchCriteria();
		userSearchCriteria.setDirections(set);
		userSearchCriteria.setRegions(set);

		when(userRepository.findDoctorsProfiles(any(Pageable.class)))
				.thenReturn(userEntityPage);

		userService.findAllExperts(userSearchCriteria, pageable);
		verify(userMapper, times(userEntityPage.getNumberOfElements())).toUserDTO(any(UserEntity.class));
	}

	@Test
	void findAllExpertsByDirectionsAndRegions_FilteredByRegionsOnly() {
		Page<UserEntity> userEntityPage = new PageImpl<>(List.of(new UserEntity(), new UserEntity()));
		Set<Integer> regionsIds = Set.of(1, 4, 6);
		Set<Integer> set = new HashSet<>();
		UserSearchCriteria userSearchCriteria = new UserSearchCriteria();
		userSearchCriteria.setDirections(set);
		userSearchCriteria.setRegions(regionsIds);

		when(userRepository.findDoctorsProfilesByRegionsIds(anySet(), any(Pageable.class)))
				.thenReturn(userEntityPage);
		userService.findAllExperts(userSearchCriteria, pageable);

		verify(userMapper, times(userEntityPage.getNumberOfElements())).toUserDTO(any(UserEntity.class));
	}

	@Test
	void findAllExpertsByDirectionsAndRegions_FilteredByDirectionsOnly() {
		Page<UserEntity> userEntityPage = new PageImpl<>(List.of(new UserEntity(), new UserEntity()));
		Set<Integer> directionsIds = Set.of(1, 4, 6);
		Set<Integer> set = new HashSet<>();
		UserSearchCriteria userSearchCriteria = new UserSearchCriteria();
		userSearchCriteria.setDirections(directionsIds);
		userSearchCriteria.setRegions(set);

		when(userRepository.findDoctorsProfilesByDirectionsIds(
				anySet(), any(Pageable.class)
		)).thenReturn(userEntityPage);

		userService.findAllExperts(userSearchCriteria, pageable);

		verify(userMapper, times(userEntityPage.getNumberOfElements())).toUserDTO(any(UserEntity.class));
	}

	@Test
	void findAllExpertsByDirectionsAndRegions_FilteredByDirectionsAndByRegions() {
		Page<UserEntity> userEntityPage = new PageImpl<>(List.of(new UserEntity(), new UserEntity()));
		Set<Integer> directionsIds = Set.of(1, 4, 6);
		Set<Integer> regionsIds = Set.of(1, 4, 6);
		UserSearchCriteria userSearchCriteria = new UserSearchCriteria();

		userSearchCriteria.setDirections(directionsIds);
		userSearchCriteria.setRegions(regionsIds);

		when(userRepository
				.findDoctorsProfiles(
						anySet(), anySet(), any(Pageable.class))
		).thenReturn(userEntityPage);
		userService.findAllExperts(userSearchCriteria, pageable);

		verify(userMapper, times(userEntityPage.getNumberOfElements())).toUserDTO(any(UserEntity.class));
	}

	@Test
	void findAllExpertsByName() {

		Set<Integer> set = new HashSet<>();
		UserSearchCriteria userSearchCriteria = new UserSearchCriteria();
		userSearchCriteria.setUserName("B");
		userSearchCriteria.setDirections(set);
		userSearchCriteria.setRegions(set);

		Page<UserEntity> userEntityPage = new PageImpl<>(List.of(new UserEntity(), new UserEntity()));

		when(userRepository
				.findDoctorsByName("B", pageable))
				.thenReturn(userEntityPage);

		userService.findAllExperts(userSearchCriteria, pageable);
		verify(userMapper, times(userEntityPage.getNumberOfElements())).toUserDTO(any(UserEntity.class));
	}

	@Test
	void findAllExpertsByName_WhenNotFound_ThrowException() {

		UserSearchCriteria userSearchCriteria = new UserSearchCriteria();
		Set<Integer> set = new HashSet<>();
		userSearchCriteria.setUserName("Иван");
		userSearchCriteria.setDirections(set);
		userSearchCriteria.setRegions(set);

		when(userRepository
				.findDoctorsByName("Иван", pageable))
				.thenThrow(new EntityNotFoundException("User does not exist"));

		assertThrows(EntityNotFoundException.class, () -> userService
				.findAllExperts(userSearchCriteria, pageable));
	}

	@Test
	void findAllExpertsByFirstNameAndLastName() {

		Set<Integer> set = new HashSet<>();

		UserSearchCriteria userSearchCriteria = new UserSearchCriteria();
		userSearchCriteria.setUserName("И И");
		userSearchCriteria.setDirections(set);
		userSearchCriteria.setRegions(set);

		Page<UserEntity> userEntityPage = new PageImpl<>(List.of(new UserEntity(), new UserEntity()));

		when(userRepository
				.findDoctorsByName(anyString(), anyString(), any(Pageable.class)))
				.thenReturn(userEntityPage);
		userService.findAllExperts(userSearchCriteria, pageable);
		verify(userMapper, times(userEntityPage.getNumberOfElements())).toUserDTO(any(UserEntity.class));
	}

	@Test
	void findAllExpertsByFirstNameAndLastName_WhenNotFound_ThrowException() {

		UserSearchCriteria userSearchCriteria = new UserSearchCriteria();
		Set<Integer> set = new HashSet<>();
		userSearchCriteria.setUserName("И И");
		userSearchCriteria.setDirections(set);
		userSearchCriteria.setRegions(set);

		when(userRepository
				.findDoctorsByName("И", "И", pageable))
				.thenThrow(new EntityNotFoundException("User does not exist"));

		assertThrows(EntityNotFoundException.class, () -> userService
				.findAllExperts(userSearchCriteria, pageable));
	}

	@Test
	void findAll() {
		userService.findAll(pageable);
		verify(userRepository, times(1)).findAll(pageable);
	}

	@Test
	void setEnableTrue() {
		UserEntity userEntity = UserEntity.builder()
				.id(1)
				.build();
		when(userRepository.findById(any(Integer.class))).thenReturn(Optional.of(userEntity));
		userService.setEnableTrue(userEntity);
		assertTrue(userEntity.getEnabled());
		verify(userRepository, times(1))
				.findById(any(Integer.class));
	}

	@Test
	void getVerificationToken() {
		String token = "950c9760-805e-449c-a966-2d0d5ebd86f4";
		VerificationToken verificationToken = VerificationToken.builder()
				.token(token)
				.build();
		when(tokenRepository.findByToken(any(String.class))).thenReturn(verificationToken);
		verificationToken = userService.getVerificationToken(token);
		assertEquals(token, verificationToken.getToken());
		verify(tokenRepository, times(1))
				.findByToken(any(String.class));
	}

	@Test
	void createVerificationToken() {
		String token = "950c9760-805e-449c-a966-2d0d5ebd86f4";
		UserEntity userEntity = UserEntity.builder().build();
		VerificationToken verificationToken = VerificationToken.builder()
				.token(token)
				.user(userEntity)
				.build();
		when(tokenRepository.save(any(VerificationToken.class))).thenReturn(verificationToken);
		userService.createVerificationToken(userEntity, token);
		verify(tokenRepository, times(1))
				.save(any(VerificationToken.class));
		assertEquals(token, verificationToken.getToken());
		assertEquals(userEntity, verificationToken.getUser());
	}

	@Test
	void existByEmail() {
		String email = "user@mail.com";
		Boolean existing = true;
		when(userRepository.existsByEmail(anyString())).thenReturn(existing);
		existing = userService.existsByEmail(email);
		assertEquals(true, existing);
		verify(userRepository, times(1))
				.existsByEmail(anyString());
	}

	@Test
	void saveUser() {
		UserEntity userEntity = UserEntity.builder()
				.id(1)
				.build();
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
		userEntity = userService.saveUser(userEntity);
		assertEquals(1, userEntity.getId());
		verify(userRepository, times(1))
				.save(any(UserEntity.class));
	}

	@Test
	void findUserByEmail() {
		String email = "some@some.com";
		UserEntity user = UserEntity.builder()
				.email(email)
				.build();
		when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(user));
		UserEntity resultUser = userService.findByEmail(email);
		verify(userRepository, times(1)).findByEmail(email);
		assertEquals(email, resultUser.getEmail());
	}

	@Test
	void findAllUser() {
		Page<UserEntity> users = new PageImpl<>(List.of(new UserEntity(), new UserEntity()));
		when(userRepository.findAll(any(Pageable.class))).thenReturn(users);
		userService.findAll(pageable);
		verify(userRepository, times(1)).findAll(pageable);
	}

	@Test
	void registerNewUser() {
		when(passwordEncoder.encode(any(String.class))).thenReturn("password");
		SignUpRequest signUpRequest = new SignUpRequest();
		signUpRequest.setName("test user");
		signUpRequest.setEmail("user@mail.com");
		signUpRequest.setPassword("password");
		UserEntity userEntity = UserEntity.builder()
				.id(1)
				.enabled(false)
				.firstName("test")
				.lastName("user")
				.email("user@mail.com")
				.status(UserStatus.NEW)
				.build();
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
		userEntity = userService.registerNewUser(signUpRequest);
		assertEquals(1, userEntity.getId());
		verify(userRepository, times(1))
				.save(any(UserEntity.class));
	}
}
