package com.softserveinc.dokazovi.security;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.exception.ResourceNotFoundException;
import com.softserveinc.dokazovi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		UserEntity user = userRepository.findByEmail(email)
				.orElseThrow(() ->
						new UsernameNotFoundException("User not found with email : " + email)
				);

		return UserPrincipal.create(user);
	}

	@Transactional
	public UserDetails loadUserById(Integer id) {
		UserEntity user = userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("User", "id", id)
		);

		return UserPrincipal.create(user);
	}
}
