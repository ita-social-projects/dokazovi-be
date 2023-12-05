package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.entity.UserLoginIpEntity;
import com.softserveinc.dokazovi.security.UserPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserLoginIpService {

    void saveUserIP(Integer userId, String ipAddress);

    List<String> getAllUserIps(UserPrincipal userPrincipal, Integer userId);
}
