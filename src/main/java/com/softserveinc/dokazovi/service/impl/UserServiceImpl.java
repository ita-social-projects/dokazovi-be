package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.user.ExpertPreviewDTO;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.mapper.UserMapper;
import com.softserveinc.dokazovi.dto.user.RandomExpertFilteringDTO;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


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
	public Page<ExpertPreviewDTO> getRandomExpertPreview(Pageable pageable, RandomExpertFilteringDTO requestBody) {
		if (CollectionUtils.isEmpty(requestBody.getDirectionsIds())) {
			return userRepository.findRandomActiveUsers(pageable)
					.map(userMapper::toExpertPreviewDTO);
		}

		return userRepository.findRandomActiveUsersByDirections(pageable, requestBody.getDirectionsIds())
				.map(userMapper::toExpertPreviewDTO);
	}

}
