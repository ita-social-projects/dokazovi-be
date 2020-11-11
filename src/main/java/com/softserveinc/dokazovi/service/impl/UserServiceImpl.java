package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.DoctorPreviewDTO;
import com.softserveinc.dokazovi.repositories.PostRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PostRepository postRepository;

	public UserServiceImpl(UserRepository userRepository,
			PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	@Override
	public Page<DoctorPreviewDTO> getExpertsPreview(Pageable pageable) {
		userRepository.findAll();
		return null;
	}
}
