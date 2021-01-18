package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.VerificationToken;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.entity.payload.SignUpRequest;
import com.softserveinc.dokazovi.mapper.UserMapper;
import com.softserveinc.dokazovi.repositories.PostRepository;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
	private PostRepository postRepository;
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

		when(userRepository.findRandomUsersByStatus(any(UserStatus.class), any(Pageable.class)))
				.thenReturn(userEntityPage);
		userService.findRandomExpertPreview(null, pageable);

		verify(userMapper, times(userEntityPage.getNumberOfElements())).toUserDTO(any(UserEntity.class));
	}

	@Test
	void getRandomExpertPreviewByDirections() {
		Page<UserEntity> userEntityPage = new PageImpl<>(List.of(new UserEntity(), new UserEntity()));
		Set<Integer> directionIds = Set.of(1, 2);

		when(userRepository.findRandomUsersByDirectionsAndStatus(anySet(), any(UserStatus.class), any(Pageable.class)))
				.thenReturn(userEntityPage);
		userService.findRandomExpertPreview(directionIds, pageable);

		verify(userMapper, times(userEntityPage.getNumberOfElements())).toUserDTO(any(UserEntity.class));
	}

	@Test
	void findAllExpertsByDirectionsAndRegions_NotFiltered() {
		Page<UserEntity> userEntityPage = new PageImpl<>(List.of(new UserEntity(), new UserEntity()));

		when(userRepository.findAllActiveUsersOrderByRating(
				any(Double.class),
				any(Double.class),
				any(Pageable.class)
		)).thenReturn(userEntityPage);
		userService.findAllExpertsByDirectionsAndRegions(null, null, pageable);

		verify(userMapper, times(userEntityPage.getNumberOfElements())).toUserDTO(any(UserEntity.class));
	}

	@Test
	void findAllExpertsByDirectionsAndRegions_FilteredByRegionsOnly() {
		Page<UserEntity> userEntityPage = new PageImpl<>(List.of(new UserEntity(), new UserEntity()));
		Set<Integer> regionsIds = Set.of(1, 4, 6);

		when(userRepository.findAllActiveUsersByRegionsIdsInOrderByRating(
				anySet(),
				any(Double.class),
				any(Double.class),
				any(Pageable.class)
		)).thenReturn(userEntityPage);
		userService.findAllExpertsByDirectionsAndRegions(null, regionsIds, pageable);

		verify(userMapper, times(userEntityPage.getNumberOfElements())).toUserDTO(any(UserEntity.class));
	}

	@Test
	void findAllExpertsByDirectionsAndRegions_FilteredByDirectionsOnly() {
		Page<UserEntity> userEntityPage = new PageImpl<>(List.of(new UserEntity(), new UserEntity()));
		Set<Integer> directionsIds = Set.of(1, 4, 6);

		when(userRepository.findAllActiveUsersByDirectionsIdsInOrderByDirectionsMatchesThenByRating(
				anySet(),
				any(Double.class),
				any(Double.class),
				any(Pageable.class)
		)).thenReturn(userEntityPage);
		userService.findAllExpertsByDirectionsAndRegions(directionsIds, null, pageable);

		verify(userMapper, times(userEntityPage.getNumberOfElements())).toUserDTO(any(UserEntity.class));
	}

	@Test
	void findAllExpertsByDirectionsAndRegions_FilteredByDirectionsAndByRegions() {
		Page<UserEntity> userEntityPage = new PageImpl<>(List.of(new UserEntity(), new UserEntity()));
		Set<Integer> directionsIds = Set.of(1, 4, 6);
		Set<Integer> regionsIds = Set.of(1, 4, 6);

		when(userRepository.findAllActiveUsersByDirectionsIdsInAndRegionsIdsInOrderByDirectionsMatchesThenByRating(
				anySet(),
				anySet(),
				any(Double.class),
				any(Double.class),
				any(Pageable.class)
		)).thenReturn(userEntityPage);
		userService.findAllExpertsByDirectionsAndRegions(directionsIds, regionsIds, pageable);

		verify(userMapper, times(userEntityPage.getNumberOfElements())).toUserDTO(any(UserEntity.class));
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
