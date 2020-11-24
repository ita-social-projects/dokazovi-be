package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.mapper.UserMapper;
import com.softserveinc.dokazovi.payload.RandomExpertRequestBody;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;
	@Mock
	private UserMapper userMapper;
	@Mock
	private Pageable pageable;
	@InjectMocks
	private UserServiceImpl userService;

	@Test
	void getRandomExpertPreview() {
		Page<UserEntity> userEntityPage = new PageImpl<>(List.of(new UserEntity(), new UserEntity()));
		RandomExpertRequestBody requestBody = new RandomExpertRequestBody();

		when(userRepository.findRandomActiveUsers(any(Pageable.class))).thenReturn(userEntityPage);
		userService.getRandomExpertPreview(pageable, requestBody);

		verify(userMapper, times(userEntityPage.getNumberOfElements())).toExpertPreviewDTO(any(UserEntity.class));
	}
}