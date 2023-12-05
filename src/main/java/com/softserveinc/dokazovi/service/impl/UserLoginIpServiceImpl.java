package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.UserLoginIpEntity;
import com.softserveinc.dokazovi.exception.EntityNotFoundException;
import com.softserveinc.dokazovi.repositories.UserLoginIpRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.service.UserLoginIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginIpServiceImpl implements UserLoginIpService {

    UserLoginIpRepository userLoginIpRepository;
    UserRepository userRepository;

    @Autowired
    public UserLoginIpServiceImpl(UserLoginIpRepository userLoginIpRepository, UserRepository userRepository) {
        this.userLoginIpRepository = userLoginIpRepository;
        this.userRepository = userRepository;
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
}
