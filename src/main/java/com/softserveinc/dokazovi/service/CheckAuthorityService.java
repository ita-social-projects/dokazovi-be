package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.security.UserPrincipal;
import org.springframework.stereotype.Service;

@Service
public interface CheckAuthorityService {
    boolean checkAuthority(UserPrincipal userPrincipal, String authority);
}