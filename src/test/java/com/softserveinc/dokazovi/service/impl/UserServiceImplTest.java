package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.mapper.UserMapper;
import com.softserveinc.dokazovi.repositories.PostRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;
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
				any(Integer.class),
				any(Integer.class),
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
				any(Integer.class),
				any(Integer.class),
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
				any(Integer.class),
				any(Integer.class),
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
				any(Integer.class),
				any(Integer.class),
				any(Pageable.class)
		)).thenReturn(userEntityPage);
		userService.findAllExpertsByDirectionsAndRegions(directionsIds, regionsIds, pageable);

		verify(userMapper, times(userEntityPage.getNumberOfElements())).toUserDTO(any(UserEntity.class));
	}

	@Test
	void findByEmail() {
		String email = "some@some.com";
		userService.findByEmail(email);
		verify(userRepository, times(1)).findByEmail(email);
	}

	@Test
	void findAll() {
		userService.findAll(pageable);
		verify(userRepository, times(1)).findAll(pageable);
	}
}