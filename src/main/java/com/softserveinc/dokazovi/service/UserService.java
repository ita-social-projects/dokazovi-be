package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.user.UserDTO;
import com.softserveinc.dokazovi.entity.PasswordResetTokenEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.VerificationToken;
import com.softserveinc.dokazovi.pojo.UserSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface UserService {

	UserEntity findByEmail(String email);

	UserEntity findUserEntityByEmail(String email);

	Page<UserEntity> findAll(Pageable pageable);

	UserDTO findExpertById(Integer userId);

	Page<UserDTO> findAllExperts(UserSearchCriteria userSearchCriteria, Pageable pageable);

	Page<UserDTO> findRandomExpertPreview(Set<Integer> directionsIds, Pageable pageable);

	void setEnableTrue(UserEntity user);

	void createVerificationToken(UserEntity user, String token);

	VerificationToken getVerificationToken(String verificationToken);

	UserEntity getById (Integer userId);

	UserEntity update(UserEntity user);

	void updatePassword(UserEntity user, String password, PasswordResetTokenEntity token);

	void sendPasswordResetToken(UserEntity user, String origin);

	boolean isPasswordMatches(UserEntity user, String password);
}
