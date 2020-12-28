package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.ProviderEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.AuthProvider;
import com.softserveinc.dokazovi.repositories.ProviderRepository;
import com.softserveinc.dokazovi.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

	private final ProviderRepository providerRepository;

	@Override
	public Optional<ProviderEntity> createLocalProviderEntityForUser(UserEntity userEntity, String email) {
		ProviderEntity providerEntity = ProviderEntity.builder()
				.user(userEntity)
				.email(email)
				.userIdByProvider(userEntity.getId().toString())
				.name(AuthProvider.LOCAL.toString())
				.build();
		return Optional.of(providerRepository.save(providerEntity));
	}

	@Override
	public boolean existsByLocalEmail(String email) {
		return providerRepository.existsByEmailAndAndName(email,AuthProvider.LOCAL.name());
	}
}
