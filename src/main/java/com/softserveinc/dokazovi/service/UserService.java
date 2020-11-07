package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

	UserEntity findByEmail(String email);

	Page<UserEntity> findAll(Pageable pageable);
}
