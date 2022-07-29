package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.user.AuthorDTO;
import com.softserveinc.dokazovi.security.UserPrincipal;

public interface AuthorService {
	AuthorDTO save(AuthorDTO authorDTO, UserPrincipal userPrincipal);
	AuthorDTO update(AuthorDTO authorDTO, UserPrincipal userPrincipal);
	Boolean delete(AuthorDTO authorDTO, UserPrincipal userPrincipal);
}
