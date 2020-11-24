package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.user.ExpertPreviewDTO;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.payload.RandomExpertRequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

	UserEntity findByEmail(String email);

	Page<UserEntity> findAll(Pageable pageable);

	Page<ExpertPreviewDTO> getRandomExpertPreview(Pageable pageable, RandomExpertRequestBody randomExpertRequestBody);

}
