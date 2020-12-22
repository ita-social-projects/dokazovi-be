package com.softserveinc.dokazovi.security;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private CustomUserDetailsService customUserDetailsService;


	@Test
	void loadUserByUsername() {
		String email = "test@test.com";
		UserEntity user = UserEntity.builder()
				.email(email)
				.build();
		when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
		UserDetails resultUser = customUserDetailsService.loadUserByUsername(email);
		verify(userRepository, times(1)).findByEmail(email);
		assertEquals(email, resultUser.getUsername());
	}

	@Test
	void loadUserById() {
		Integer id = 1;
		String email = "test@test.com";
		UserEntity userEntity = UserEntity.builder()
				.id(id)
				.email(email)
				.build();
		when(userRepository.findById(id))
				.thenReturn(Optional.of(userEntity));
		UserDetails resultUser = customUserDetailsService.loadUserById(id);
		verify(userRepository, times(1)).findById(id);
		assertEquals(email, resultUser.getUsername());
	}
}
