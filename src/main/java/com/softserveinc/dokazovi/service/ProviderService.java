package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.entity.ProviderEntity;
import com.softserveinc.dokazovi.entity.UserEntity;

import java.util.Optional;

public interface ProviderService {

    Optional<ProviderEntity> createLocalProviderEntityForUser(UserEntity userEntity, String email);

    boolean existsByLocalEmail(String email);

}
