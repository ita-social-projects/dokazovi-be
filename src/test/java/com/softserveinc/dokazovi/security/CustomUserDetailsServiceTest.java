package com.softserveinc.dokazovi.security;

import com.softserveinc.dokazovi.entity.ProviderEntity;
import com.softserveinc.dokazovi.entity.RoleEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

	@Mock
	private UserEntity userEntity;

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserPrincipal userPrincipal;

	@InjectMocks
	private CustomUserDetailsService customUserDetailsService;


	@Test
	void loadUserByUsername() {
		String email = "test@test.com";
		RoleEntity roleEntity = RoleEntity.builder().id(1).name("ROLE_DOCTOR").build();
		UserEntity user = UserEntity.builder()
				.id(1)
				.status(UserStatus.ACTIVE)
				.email(email)
				.role(roleEntity)
				.enabled(true)
				.build();
		ProviderEntity providerEntity = ProviderEntity.builder().id(1).email(email).name("LOCAL").userIdByProvider("1")
				.user(user).build();
		Set<ProviderEntity> providerEntities = new HashSet<>();
		providerEntities.add(providerEntity);
		user.setUserProviderEntities(providerEntities);
		when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
		UserDetails resultUser = customUserDetailsService.loadUserByUsername(email);
		verify(userRepository, times(1)).findByEmail(email);
		assertEquals(email, resultUser.getUsername());
	}

	@Test
	void loadUserById() {
		Integer id = 1;
		String email = "test@test.com";
		RoleEntity roleEntity = RoleEntity.builder().id(1).name("ROLE_DOCTOR").build();
		UserEntity userEntity = UserEntity.builder()
				.id(1)
				.status(UserStatus.ACTIVE)
				.email(email)
				.role(roleEntity)
				.enabled(true)
				.build();
		ProviderEntity providerEntity = ProviderEntity.builder().id(1).email(email).name("LOCAL").userIdByProvider("1")
				.user(userEntity).build();
		Set<ProviderEntity> providerEntities = new HashSet<>();
		providerEntities.add(providerEntity);
		userEntity.setUserProviderEntities(providerEntities);
		when(userRepository.findById(id))
				.thenReturn(Optional.of(userEntity));
		UserDetails resultUser = customUserDetailsService.loadUserById(id);
		verify(userRepository, times(1)).findById(id);
		assertEquals(email, resultUser.getUsername());
	}
}
