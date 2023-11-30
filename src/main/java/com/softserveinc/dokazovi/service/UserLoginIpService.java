package com.softserveinc.dokazovi.service;

import org.springframework.stereotype.Service;

@Service
public interface UserLoginIpService {

    void saveUserIP(Integer userId, String ipAddress);
}
