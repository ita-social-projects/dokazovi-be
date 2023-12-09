package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.UserLoginIpEntity;
import com.softserveinc.dokazovi.exception.EntityNotFoundException;
import com.softserveinc.dokazovi.exception.ForbiddenPermissionsException;
import com.softserveinc.dokazovi.repositories.UserLoginIpRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.CheckAuthorityService;
import com.softserveinc.dokazovi.service.UserLoginIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserLoginIpServiceImpl implements UserLoginIpService {

    UserLoginIpRepository userLoginIpRepository;
    UserRepository userRepository;
    CheckAuthorityService checkAuthorityService;

    @Autowired
    public UserLoginIpServiceImpl(UserLoginIpRepository userLoginIpRepository,
            UserRepository userRepository,
            CheckAuthorityService checkAuthorityService) {
        this.userLoginIpRepository = userLoginIpRepository;
        this.userRepository = userRepository;
        this.checkAuthorityService = checkAuthorityService;
    }

    @Override
    public void saveUserIP(Integer userId, String ipAddress) {
        boolean exists = userLoginIpRepository.existsByUserIdAndIpAddress(userId, ipAddress);

        if (!exists) {
            userRepository.findById(userId).ifPresentOrElse(user -> {
                UserLoginIpEntity userLoginIpEntity = UserLoginIpEntity.builder()
                        .user(user)
                        .ipAddress(ipAddress)
                        .build();

                userLoginIpRepository.save(userLoginIpEntity);
            }, () -> {
                throw new EntityNotFoundException("There is no user with id " + userId);
            });
        }
    }

    @Override
    public List<String> getAllUserIps(UserPrincipal userPrincipal, Integer userId) {
        if (checkAuthorityService.checkAuthority(userPrincipal, "EDIT_AUTHOR")) {
            List<UserLoginIpEntity> ipEntities = userLoginIpRepository.findAllByUserId(userId);
            return ipEntities.stream()
                    .map(UserLoginIpEntity::getIpAddress)
                    .collect(Collectors.toList());
        } else {
            throw new ForbiddenPermissionsException();
        }
    }
}
