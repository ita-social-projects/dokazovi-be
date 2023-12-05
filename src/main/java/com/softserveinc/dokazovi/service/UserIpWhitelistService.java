package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.user.UserIpWhitelistDTO;
import com.softserveinc.dokazovi.security.UserPrincipal;
import org.springframework.stereotype.Service;

@Service
public interface UserIpWhitelistService {

    void updateUserIpWhitelist(UserPrincipal userPrincipal, UserIpWhitelistDTO userIpWhitelistDTO);

    boolean isIpWhitelisted(Integer userId, String ip);
}
