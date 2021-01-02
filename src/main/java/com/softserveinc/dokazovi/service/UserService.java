package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.user.UserDTO;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;


public interface UserService {

	UserEntity findByEmail(String email);

	Page<UserEntity> findAll(Pageable pageable);

	UserDTO findExpertById(Integer userId);

	Page<UserDTO> findAllExpertsByDirectionsAndRegions(Set<Integer> directionsIds, Set<Integer> regionsIds,
			Pageable pageable);

	Page<UserDTO> findRandomExpertPreview(Set<Integer> directionsIds, Pageable pageable);

	Integer getUsersCountHavingPostWithStatus(PostStatus postsStatus);

	Integer getAllPostsCountByStatus(PostStatus postStatus);

}
