package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.post.LatestPostDTO;
import com.softserveinc.dokazovi.dto.user.ExpertPreviewDTO;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.mapper.UserMapper;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public UserEntity findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Page<UserEntity> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public Page<ExpertPreviewDTO> getExpertsPreview(Pageable pageable, Integer number) {
		List<ExpertPreviewDTO> expertPreviewDTOS = new ArrayList<>();
		List<UserEntity> userEntities = userRepository.findRandomActiveUsers(number);
		userEntities.forEach((n) -> expertPreviewDTOS.add(userMapper.toExpertPreviewDTO(n)));
		Page<ExpertPreviewDTO> page = new PageImpl<>(expertPreviewDTOS);
		return page;
	}

}
