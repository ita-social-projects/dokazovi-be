package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.user.UserDTO;
import com.softserveinc.dokazovi.dto.user.UserPasswordDTO;
import com.softserveinc.dokazovi.dto.user.UserPublicAndPrivateEmailDTO;
import com.softserveinc.dokazovi.dto.user.UserStatusDTO;
import com.softserveinc.dokazovi.dto.user.UserWhitelistStatusDTO;
import com.softserveinc.dokazovi.entity.PasswordResetTokenEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.VerificationToken;
import com.softserveinc.dokazovi.pojo.UserSearchCriteria;
import com.softserveinc.dokazovi.security.UserPrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface UserService {

    UserEntity findByEmail(String email);

    UserEntity findUserEntityByEmail(String email);

    Page<UserEntity> findAll(Pageable pageable);

    UserDTO findExpertById(Integer userId);

    UserDTO findExpertByUserId(Integer userId);

    Page<UserDTO> findAllExperts(UserSearchCriteria userSearchCriteria, Pageable pageable);



    Page<UserDTO> findRandomExpertPreview(Set<Integer> directionsIds, Pageable pageable);

    void setEnabled(Integer authorId, boolean isEnabled);

    void createVerificationToken(UserEntity user, String token);

    VerificationToken getVerificationToken(String verificationToken);

    UserEntity getById(Integer authorId);

    UserEntity getByUserId(Integer userId);

    UserEntity update(UserEntity user);

    void updatePassword(UserEntity user, String password, PasswordResetTokenEntity token);

    void sendPasswordResetToken(UserEntity user, String origin);

    boolean isPasswordMatches(UserEntity user, String password);

    void sendActivationToken(Integer userId, String email, String origin);

    void activateUser(UserPasswordDTO userPasswordDTO);

    UserPublicAndPrivateEmailDTO getAllPublicAndPrivateEmails();

    void changeStatus(UserStatusDTO userStatusDTO);

    void changeWhitelistStatus(UserPrincipal userPrincipal, UserWhitelistStatusDTO userWhitelistStatusDTO);
}
