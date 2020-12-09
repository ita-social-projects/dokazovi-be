package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.ProviderEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.AuthProvider;
import com.softserveinc.dokazovi.repositories.ProviderRepository;
import com.softserveinc.dokazovi.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	private ProviderRepository providerRepository;

	@Override
	public Optional<ProviderEntity> createLocalProviderEntityForUser(UserEntity userEntity, String email) {
		ProviderEntity providerEntity = ProviderEntity.builder().
				user(userEntity).
				email(email).
				userIdByProvider(userEntity.getId().toString()).
				name(AuthProvider.local.toString()).
				build();
		return Optional.of(providerRepository.save(providerEntity));
	}
}
