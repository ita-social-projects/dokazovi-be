package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.user.UserDTO;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.mapper.UserMapper;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	public UserEntity findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Page<UserEntity> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public UserDTO findExpertById(Integer userId) {
		return userMapper.toUserDTO(userRepository.findById(userId).orElse(null));
	}

	@Override
	public Page<UserDTO> findAllExpertsByDirectionsAndRegions(Set<Integer> directionsIds, Set<Integer> regionsIds,
			Pageable pageable) {
		if (CollectionUtils.isEmpty(directionsIds) && CollectionUtils.isEmpty(regionsIds)) {
			return userRepository
					.findAllByStatusOrderByRating(UserStatus.ACTIVE, pageable)
					.map(userMapper::toUserDTO);
		} else if (CollectionUtils.isEmpty(directionsIds)) {
			return userRepository
					.findAllByRegionsIdsInAndStatusOrderByRating(regionsIds, UserStatus.ACTIVE, pageable)
					.map(userMapper::toUserDTO);
		} else if (CollectionUtils.isEmpty(regionsIds)) {
			return userRepository
					.findAllByDirectionsIdsInAndStatusOrderByDirectionsMatchesThenByRating(
							directionsIds, UserStatus.ACTIVE, pageable)
					.map(userMapper::toUserDTO);
		}

		return userRepository
				.findAllByDirectionsIdsInAndRegionsIdsInAndStatusOrderByDirectionsMatchesThenByRating(
						directionsIds, regionsIds, UserStatus.ACTIVE, pageable)
				.map(userMapper::toUserDTO);
	}

	@Override
	public Page<UserDTO> findRandomExpertPreview(Set<Integer> directionsIds, Pageable pageable) {
		if (CollectionUtils.isEmpty(directionsIds)) {
			return userRepository.findRandomUsersByStatus(UserStatus.ACTIVE, pageable)
					.map(userMapper::toUserDTO);
		}

		return userRepository.findRandomUsersByDirectionsAndStatus(directionsIds, UserStatus.ACTIVE, pageable)
				.map(userMapper::toUserDTO);
	}

}
