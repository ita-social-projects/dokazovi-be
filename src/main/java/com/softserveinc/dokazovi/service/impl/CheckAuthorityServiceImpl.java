package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.CheckAuthorityService;
import org.springframework.stereotype.Service;

@Service
public class CheckAuthorityServiceImpl implements CheckAuthorityService {

    @Override
    public boolean checkAuthority(UserPrincipal userPrincipal, String authority) {
        return userPrincipal.getAuthorities().stream().anyMatch(grantedAuthority ->
                grantedAuthority.getAuthority().equals(authority));
    }
}
