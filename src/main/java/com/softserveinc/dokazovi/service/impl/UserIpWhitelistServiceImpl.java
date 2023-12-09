package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.user.UserIpWhitelistDTO;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.UserIpWhitelistEntity;
import com.softserveinc.dokazovi.exception.EntityNotFoundException;
import com.softserveinc.dokazovi.exception.ForbiddenPermissionsException;
import com.softserveinc.dokazovi.repositories.UserIpWhitelistRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.CheckAuthorityService;
import com.softserveinc.dokazovi.service.UserIpWhitelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserIpWhitelistServiceImpl implements UserIpWhitelistService {

    UserRepository userRepository;
    UserIpWhitelistRepository userIpWhitelistRepository;
    CheckAuthorityService checkAuthorityService;

    @Autowired
    public UserIpWhitelistServiceImpl(UserRepository userRepository,
            UserIpWhitelistRepository userIpWhitelistRepository,
            CheckAuthorityService checkAuthorityService) {
        this.userRepository = userRepository;
        this.userIpWhitelistRepository = userIpWhitelistRepository;
        this.checkAuthorityService = checkAuthorityService;
    }

    @Override
    public void updateUserIpWhitelist(UserPrincipal userPrincipal, UserIpWhitelistDTO userIpWhitelistDTO) {
        if (checkAuthorityService.checkAuthority(userPrincipal, "EDIT_AUTHOR")) {

            if (userIpWhitelistDTO.getWhitelistIps().size() > 10) {
                throw new IllegalArgumentException("Cannot have more than 10 IPs in the whitelist");
            }

            UserEntity user = userRepository.findById(userIpWhitelistDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "User with id " + userIpWhitelistDTO.getId() + " not found"));

            List<UserIpWhitelistEntity> existingEntries = userIpWhitelistRepository.findAllByUser(user);
            Set<String> existingWhitelistedIps = existingEntries.stream()
                    .map(UserIpWhitelistEntity::getWhitelistIp)
                    .collect(Collectors.toSet());

            Set<String> updatedIps = new HashSet<>(userIpWhitelistDTO.getWhitelistIps());

            existingWhitelistedIps.stream()
                    .filter(ip -> !updatedIps.contains(ip))
                    .forEach(ip -> existingEntries.stream()
                            .filter(entry -> entry.getWhitelistIp().equals(ip))
                            .findFirst()
                            .ifPresent(entryToRemove -> userIpWhitelistRepository.delete(entryToRemove)));

            updatedIps.stream()
                    .filter(ip -> !existingWhitelistedIps.contains(ip))
                    .forEach(ip -> userIpWhitelistRepository.save(
                            new UserIpWhitelistEntity(null, user, ip)));
        } else {
            throw new ForbiddenPermissionsException();
        }
    }

    @Override
    public boolean isIpWhitelisted(Integer userId, String ip) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found"));

        return userIpWhitelistRepository.existsByUserAndWhitelistIp(user, ip);
    }
}
