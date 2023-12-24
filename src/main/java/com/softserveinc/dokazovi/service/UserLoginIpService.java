package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.security.UserPrincipal;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface UserLoginIpService {

    void saveUserIP(Integer userId, String ipAddress);

    List<String> getAllUserIps(UserPrincipal userPrincipal, Integer userId);

    String getClientIp(HttpServletRequest request);
}
