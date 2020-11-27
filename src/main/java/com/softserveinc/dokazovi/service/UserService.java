package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.user.UserDTO;
import com.softserveinc.dokazovi.dto.user.RandomExpertFilteringDTO;
import com.softserveinc.dokazovi.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

	UserDTO findExpertById(Integer userId);

	UserEntity findByEmail(String email);

	Page<UserEntity> findAll(Pageable pageable);

	Page<UserDTO> getRandomExpertPreview(Pageable pageable, RandomExpertFilteringDTO randomExpertFilteringDTO);

}
